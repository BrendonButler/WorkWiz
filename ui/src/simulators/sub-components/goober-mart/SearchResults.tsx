import { useRef, useEffect, useCallback } from 'react';
import { ScrollArea } from '@/components/ui/scroll-area';
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from '@/components/ui/tooltip';
import { ShoppingItem } from '@/simulators/sub-types/Item';

interface SearchResultsProps {
  searchResults: ShoppingItem[];
  addToBasket: (item: ShoppingItem) => void;
  hasMore: boolean;
  loadMoreItems: () => void;
  isLoading: boolean;
}

export function SearchResults({
  searchResults,
  addToBasket,
  hasMore,
  loadMoreItems,
  isLoading
}: SearchResultsProps) {
  const scrollAreaRef = useRef<HTMLDivElement>(null);
  const loadingRef = useRef<HTMLDivElement>(null);

  const handleScroll = useCallback(() => {
    const scrollArea = scrollAreaRef.current;
    const loadingElement = loadingRef.current;
    if (!scrollArea || !loadingElement) return;

    const { scrollTop, scrollHeight, clientHeight } = scrollArea;

    if (scrollHeight - scrollTop <= clientHeight + 50 && hasMore && !isLoading) {
      loadMoreItems();
    }
  }, [hasMore, loadMoreItems, isLoading]);

  useEffect(() => {
    const scrollArea = scrollAreaRef.current;
    if (!scrollArea) return;

    scrollArea.addEventListener('scroll', handleScroll);
    return () => scrollArea.removeEventListener('scroll', handleScroll);
  }, [handleScroll]);

  if (searchResults.length === 0 && !isLoading) return null;

  return (
    <div className='absolute left-0 right-0 mt-0 bg-white border border-gray-200 border-t-0 rounded-b-md shadow-sm z-20 overflow-hidden'>
      <ScrollArea className='max-h-60' ref={scrollAreaRef}>
        {searchResults.map((item, index) => (
          <TooltipProvider key={`${item.id}-${index}`}>
            <Tooltip>
              <TooltipTrigger asChild>
                <div
                  className='p-2 hover:bg-gray-100 cursor-pointer flex justify-between items-center'
                  onClick={() => addToBasket(item)}
                >
                  <div className='font-medium'>{item.name}</div>
                  <div className='text-sm text-gray-500'>${item.price.toFixed(2)}</div>
                </div>
              </TooltipTrigger>
              <TooltipContent>
                <p>{item.description}</p>
              </TooltipContent>
            </Tooltip>
          </TooltipProvider>
        ))}
        {(hasMore || isLoading) && (
          <div ref={loadingRef} className='p-2 text-center text-gray-500'>
            {isLoading ? 'Loading...' : 'Scroll to load more...'}
          </div>
        )}
      </ScrollArea>
    </div>
  );
}
