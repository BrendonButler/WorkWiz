import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Plus, Minus, X } from 'lucide-react';
import { motion } from 'framer-motion';
import { BasketItem as BasketItemType } from '@/simulators/sub-types/Item';

interface BasketItemProps {
  item: BasketItemType;
  updateQuantity: (id: number, newQuantity: number) => void;
  removeItem: (id: number) => void;
}

export function BasketItem({ item, updateQuantity, removeItem }: BasketItemProps) {
  const handleQuantityChange = (value: string) => {
    const newQuantity = parseInt(value, 10);
    if (!isNaN(newQuantity)) {
      updateQuantity(item.id, newQuantity);
    }
  };

  return (
    <motion.div
      data-testid={`basket-item-${item.id}`}
      className='flex items-center justify-between py-2 border-b'
      initial={{ opacity: 0, x: -50 }}
      animate={{ opacity: 1, x: 0 }}
      exit={{ opacity: 0, x: 50 }}
      transition={{ duration: 0.2 }}
    >
      <div>
        <span className='font-medium'>{item.name}</span>
        <div className='text-sm text-gray-500'>${item.price.toFixed(2)}</div>
      </div>
      <div className='flex items-center space-x-2'>
        <Button
          dataTestId={`decrement-item-${item.id}`}
          size='sm'
          variant='outline'
          onClick={() => updateQuantity(item.id, item.quantity - 1)}
        >
          <Minus className='h-4 w-4' />
        </Button>
        <Input
          dataTestId={`quantity-item-${item.id}`}
          type='text'
          value={item.quantity}
          onChange={(e) => handleQuantityChange(e.target.value)}
          className='w-12 text-center'
          onKeyDown={(e) => {
            if (!/[0-9]/.test(e.key)) {
              e.preventDefault();
            }
          }}
        />
        <Button
          dataTestId={`increment-item-${item.id}`}
          size='sm'
          variant='outline'
          onClick={() => updateQuantity(item.id, item.quantity + 1)}
        >
          <Plus className='h-4 w-4' />
        </Button>
        <Button
          dataTestId={`remove-item-${item.id}`}
          size='sm'
          variant='destructive'
          onClick={() => removeItem(item.id)}
        >
          <X className='h-4 w-4' />
        </Button>
      </div>
    </motion.div>
  );
}
