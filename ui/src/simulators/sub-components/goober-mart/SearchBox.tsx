import { useRef, useCallback, useEffect } from 'react';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { ShoppingBasket } from 'lucide-react';
import { SearchResults } from './SearchResults';
import { ShoppingItem } from '@/simulators/sub-types/Item';

interface SearchBoxProps {
  input: string;
  setInput: (value: string) => void;
  searchResults: ShoppingItem[];
  addToBasket: (item: ShoppingItem) => void;
  handleKeyPress: (e: React.KeyboardEvent) => void;
  hasMore: boolean;
  loadMoreItems: () => void;
  searchItems: (query: string) => void;
  isLoading: boolean;
}

export function SearchBox({
  input,
  setInput,
  searchResults,
  addToBasket,
  handleKeyPress,
  hasMore,
  loadMoreItems,
  searchItems,
  isLoading
}: SearchBoxProps) {
  const searchInputRef = useRef<HTMLInputElement>(null);

  const handleAddToBasket = useCallback(() => {
    if (searchResults.length > 0) {
      addToBasket(searchResults[0]);
    }
  }, [addToBasket, searchResults]);

  return (
    <div className='flex flex-col space-y-2 relative'>
      <div className='flex space-x-2'>
        <div className='flex-grow relative'>
          <Input
            ref={searchInputRef}
            type='text'
            placeholder='Search Product'
            value={input}
            onChange={(e) => setInput(e.target.value)}
            onKeyPress={handleKeyPress}
            className='w-full'
          />
          <SearchResults
            searchResults={searchResults}
            addToBasket={addToBasket}
            hasMore={hasMore}
            loadMoreItems={loadMoreItems}
            isLoading={isLoading}
          />
        </div>
        <Button onClick={handleAddToBasket}>
          <ShoppingBasket className='mr-2 h-4 w-4' /> Add
        </Button>
      </div>
    </div>
  );
}
