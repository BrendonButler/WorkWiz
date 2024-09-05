import { useState } from 'react';
import { Button } from '@/components/ui/button';
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger
} from '@/components/ui/dialog';
import { Trash2 } from 'lucide-react';

interface ClearBasketButtonProps {
  onClearBasket: () => void;
}

export function ClearBasketButton({ onClearBasket }: ClearBasketButtonProps) {
  const [isDialogOpen, setIsDialogOpen] = useState(false);

  const handleClearBasket = () => {
    onClearBasket();
    setIsDialogOpen(false);
  };

  return (
    <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
      <DialogTrigger asChild>
        <Button variant='destructive' size='sm'>
          <Trash2 className='mr-2 h-4 w-4' /> Empty Basket
        </Button>
      </DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Clear Basket</DialogTitle>
          <DialogDescription>
            Are you sure you want to clear the basket? All items will be removed!
          </DialogDescription>
        </DialogHeader>
        <DialogFooter>
          <Button variant='outline' onClick={() => setIsDialogOpen(false)}>
            Cancel
          </Button>
          <Button variant='destructive' onClick={handleClearBasket}>
            Clear Basket
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
