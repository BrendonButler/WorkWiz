/* created using v0 */
/* Point of Sale (POS) Simulator */
'use client';

import { useState, useRef } from 'react';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { ScrollArea } from '@/components/ui/scroll-area';
import { ShoppingBasket, Plus, Minus, X, Trash2 } from 'lucide-react';
import { motion, AnimatePresence } from 'framer-motion';

interface BasketItem {
  id: string;
  quantity: number;
}

export default function Component() {
  const [input, setInput] = useState('');
  const [basket, setBasket] = useState<BasketItem[]>([]);
  const posSimulatorRef = useRef<HTMLDivElement>(null);

  const addToBasket = () => {
    if (input.trim() === '') return;

    setBasket((prevBasket) => {
      const existingItem = prevBasket.find((item) => item.id === input);
      if (existingItem) {
        return prevBasket.map((item) =>
          item.id === input ? { ...item, quantity: item.quantity + 1 } : item
        );
      } else {
        return [...prevBasket, { id: input, quantity: 1 }];
      }
    });

    console.log('Basket updated:', [...basket, { id: input, quantity: 1 }]);
    setInput('');
  };

  const handleKeyPress = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') {
      addToBasket();
    }
  };

  const updateQuantity = (id: string, change: number) => {
    setBasket((prevBasket) =>
      prevBasket
        .map((item) =>
          item.id === id ? { ...item, quantity: Math.max(0, item.quantity + change) } : item
        )
        .filter((item) => item.quantity > 0)
    );
  };

  const removeItem = (id: string) => {
    setBasket((prevBasket) => prevBasket.filter((item) => item.id !== id));
  };

  const emptyBasket = () => {
    setBasket([]);
  };

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
          <h1 className='text-2xl font-bold text-center mb-6'>POS Simulator</h1>
          <div className='flex space-x-2'>
            <Input
              type='text'
              placeholder='Enter SKU/UPC'
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyPress={handleKeyPress}
              className='flex-grow'
            />
            <Button onClick={addToBasket}>
              <ShoppingBasket className='mr-2 h-4 w-4' /> Add
            </Button>
          </div>
          <div className='mt-4 text-sm text-gray-600'>
            Items in basket: {basket.reduce((sum, item) => sum + item.quantity, 0)}
          </div>
        </motion.div>

        <AnimatePresence>
          {basket.length > 0 && (
            <motion.div
              className='w-full min-h-basket bg-white rounded-lg shadow-md p-6 flex flex-col'
              initial={{ y: '20vh', opacity: 0, height: 0 }}
              animate={{ y: '10vh', opacity: 1, height: 'auto', maxHeight: '50vh' }}
              exit={{ opacity: 0, height: 0 }}
              transition={{ duration: 0.3 }}
            >
              <div id='shopping-basket' className='flex justify-between items-center mb-4'>
                <h2 className='text-xl font-semibold'>Shopping Basket</h2>
                <Button variant='destructive' size='sm' onClick={emptyBasket}>
                  <Trash2 className='mr-2 h-4 w-4' /> Empty Basket
                </Button>
              </div>
              <ScrollArea
                className='flex-grow pr-4'
                style={{ minHeight: '53px', maxHeight: 'calc(50vh - 10rem)' }}
              >
                <AnimatePresence>
                  {basket.map((item) => (
                    <motion.div
                      key={item.id}
                      className='flex items-center justify-between py-2 border-b'
                      initial={{ opacity: 0, x: -50 }}
                      animate={{ opacity: 1, x: 0 }}
                      exit={{ opacity: 0, x: 50 }}
                      transition={{ duration: 0.2 }}
                    >
                      <span className='font-medium'>{item.id}</span>
                      <div className='flex items-center space-x-2'>
                        <Button
                          size='sm'
                          variant='outline'
                          onClick={() => updateQuantity(item.id, -1)}
                        >
                          <Minus className='h-4 w-4' />
                        </Button>
                        <span>{item.quantity}</span>
                        <Button
                          size='sm'
                          variant='outline'
                          onClick={() => updateQuantity(item.id, 1)}
                        >
                          <Plus className='h-4 w-4' />
                        </Button>
                        <Button size='sm' variant='destructive' onClick={() => removeItem(item.id)}>
                          <X className='h-4 w-4' />
                        </Button>
                      </div>
                    </motion.div>
                  ))}
                </AnimatePresence>
              </ScrollArea>
            </motion.div>
          )}
        </AnimatePresence>
      </div>
    </div>
  );
}
