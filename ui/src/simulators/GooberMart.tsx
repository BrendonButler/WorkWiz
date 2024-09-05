/* created using v0 */
/* Point of Sale (POS) Simulator */
'use client';

import { useState, useRef, useCallback, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { useDebounce } from 'use-debounce';
import { BasketItem, ShoppingItem } from '@/simulators/sub-types/Item';
import { SearchBox } from '@/simulators/sub-components/goober-mart/SearchBox';
import { Basket } from '@/simulators/sub-components/goober-mart/Basket';

export default function GooberMart() {
  const [input, setInput] = useState('');
  const [basket, setBasket] = useState<BasketItem[]>([]);
  const [searchResults, setSearchResults] = useState<ShoppingItem[]>([]);
  const [debouncedInput] = useDebounce(input, 300);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const [isLoading, setIsLoading] = useState(false);
  const posSimulatorRef = useRef<HTMLDivElement>(null);
  const lastSearchRef = useRef('');
  const searchTimeoutRef = useRef<NodeJS.Timeout | null>(null);

  const searchItems = useCallback(
    async (query: string, pageNum: number = 0) => {
      if (isLoading || query.trim() === '') return;
      setIsLoading(true);
      try {
        const response = await fetch(
          `http://localhost:8080/api/shopping/items/search?query=${query}&page=${pageNum}&size=10`
        );
        if (!response.ok) throw new Error('Failed to fetch items');
        const data: ShoppingItem[] = await response.json();
        setSearchResults((prev) => {
          if (pageNum === 0) {
            return data;
          } else {
            const newItems = data.filter(
              (item) => !prev.some((prevItem) => prevItem.id === item.id)
            );
            return [...prev, ...newItems];
          }
        });
        setHasMore(data.length === 10);
        setPage(pageNum);
        lastSearchRef.current = query;
      } catch (error) {
        console.error('Error searching items:', error);
        setSearchResults([]);
        setHasMore(false);
      } finally {
        setIsLoading(false);
      }
    },
    [isLoading]
  );

  useEffect(() => {
    if (debouncedInput.trim() !== '' && debouncedInput !== lastSearchRef.current) {
      setPage(0);
      searchItems(debouncedInput, 0);
    } else if (debouncedInput.trim() === '') {
      setSearchResults([]);
      setHasMore(false);
      lastSearchRef.current = '';
    }
  }, [debouncedInput, searchItems]);

  const loadMoreItems = useCallback(() => {
    if (lastSearchRef.current && hasMore && !isLoading) {
      searchItems(lastSearchRef.current, page + 1);
    }
  }, [hasMore, isLoading, page, searchItems]);

  const addToBasket = useCallback((item: ShoppingItem) => {
    setBasket((prevBasket) => {
      const existingItem = prevBasket.find((basketItem) => basketItem.id === item.id);
      if (existingItem) {
        return prevBasket.map((basketItem) =>
          basketItem.id === item.id
            ? { ...basketItem, quantity: basketItem.quantity + 1 }
            : basketItem
        );
      } else {
        return [...prevBasket, { ...item, quantity: 1 }];
      }
    });

    setInput('');
    setSearchResults([]);
  }, []);

  const handleKeyPress = useCallback(
    (e: React.KeyboardEvent) => {
      if (e.key === 'Enter' && searchResults.length > 0) {
        addToBasket(searchResults[0]);
      }
    },
    [addToBasket, searchResults]
  );

  const updateQuantity = useCallback((id: number, newQuantity: number) => {
    setBasket((prevBasket) =>
      prevBasket
        .map((item) => (item.id === id ? { ...item, quantity: Math.max(0, newQuantity) } : item))
        .filter((item) => item.quantity > 0)
    );
  }, []);

  const removeItem = useCallback((id: number) => {
    setBasket((prevBasket) => prevBasket.filter((item) => item.id !== id));
  }, []);

  const emptyBasket = useCallback(() => {
    setBasket([]);
  }, []);

  const totalItems = basket.reduce((sum, item) => sum + item.quantity, 0);
  const totalPrice = basket.reduce((sum, item) => sum + item.price * item.quantity, 0);

  return (
    <div className='flex flex-col items-center min-h-screen bg-gray-100 p-4'>
      <div className='w-full max-w-md flex flex-col items-start justify-center space-y-4'>
        <motion.div
          ref={posSimulatorRef}
          className='w-full bg-white rounded-lg shadow-md p-6 z-10'
          initial={{ y: '25vh' }}
          animate={{ y: basket.length > 0 ? '10vh' : '25vh' }}
          transition={{ duration: 0.5 }}
        >
          <h1 className='text-2xl font-bold text-center mb-6'>Goober-Mart</h1>
          <SearchBox
            input={input}
            setInput={setInput}
            searchResults={searchResults}
            addToBasket={addToBasket}
            handleKeyPress={handleKeyPress}
            hasMore={hasMore}
            loadMoreItems={loadMoreItems}
            searchItems={(query) => searchItems(query, 0)}
            isLoading={isLoading}
          />
          <div className='mt-4 text-sm text-gray-600 flex justify-between items-center'>
            <div>Total: ${totalPrice.toFixed(2)}</div>
            <div>Items in basket: {totalItems}</div>
          </div>
        </motion.div>

        <AnimatePresence>
          {basket.length > 0 && (
            <motion.div
              className='w-full'
              initial={{ y: '20vh', opacity: 0, height: 0 }}
              animate={{ y: '10vh', opacity: 1, height: 'auto', maxHeight: '50vh' }}
              exit={{ opacity: 0, height: 0 }}
              transition={{ duration: 0.3 }}
            >
              <Basket
                basket={basket}
                updateQuantity={updateQuantity}
                removeItem={removeItem}
                emptyBasket={emptyBasket}
              />
            </motion.div>
          )}
        </AnimatePresence>
      </div>
    </div>
  );
}
