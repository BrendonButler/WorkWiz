import { ScrollArea } from '@/components/ui/scroll-area';
import { AnimatePresence } from 'framer-motion';
import { BasketItem } from './BasketItem';
import { ClearBasketButton } from '@/simulators/sub-components/goober-mart/ClearBasketButton';
import { BasketItem as BasketItemType } from '@/simulators/sub-types/Item';

interface BasketProps {
  basket: BasketItemType[];
  updateQuantity: (id: number, newQuantity: number) => void;
  removeItem: (id: number) => void;
  emptyBasket: () => void;
}

export function Basket({ basket, updateQuantity, removeItem, emptyBasket }: BasketProps) {
  return (
    <div className='w-full min-h-basket bg-white rounded-lg shadow-md p-6 flex flex-col'>
      <div
        id='shopping-basket'
        data-testid={'shopping-basket'}
        className='flex justify-between items-center mb-4'
      >
        <h2 className='text-xl font-semibold'>Shopping Basket</h2>
        <ClearBasketButton onClearBasket={emptyBasket} />
      </div>
      <ScrollArea
        className='flex-grow pr-4'
        style={{ minHeight: '53px', maxHeight: 'calc(50vh - 10rem)' }}
      >
        <AnimatePresence>
          {basket.map((item) => (
            <BasketItem
              key={item.id}
              item={item}
              updateQuantity={updateQuantity}
              removeItem={removeItem}
            />
          ))}
        </AnimatePresence>
      </ScrollArea>
    </div>
  );
}
