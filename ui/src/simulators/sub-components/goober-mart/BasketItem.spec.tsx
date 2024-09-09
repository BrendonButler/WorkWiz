import { BasketItem } from './BasketItem';
import { BasketItem as BasketItemType } from '@/simulators/sub-types/Item';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';

describe('BasketItem', () => {
  it('renders', () => {
    const item: BasketItemType = {
      id: 1,
      name: 'Apple',
      description: 'A sweet, juicy treat!',
      price: 1.99,
      quantity: 1
    };

    render(<BasketItem item={item} updateQuantity={() => {}} removeItem={() => {}} />);
    const basketItem = screen.getByTestId('basket-item-1');

    expect(basketItem).toBeInTheDocument();
  });

    it('updates quantity', () => {
        const item: BasketItemType = {
        id: 1,
        name: 'Apple',
        description: 'A sweet, juicy treat!',
        price: 1.99,
        quantity: 1
        };

        const updateQuantity = jest.fn();
        render(<BasketItem item={item} updateQuantity={updateQuantity} removeItem={() => {}} />);
        const quantityInput = screen.getByTestId('quantity-item-1');
        fireEvent.change(quantityInput, { target: { value: '5' } });

        expect(updateQuantity).toHaveBeenCalledWith(1, 5);
    });

    it('removes item', () => {
        const item: BasketItemType = {
        id: 1,
        name: 'Apple',
        description: 'A sweet, juicy treat!',
        price: 1.99,
        quantity: 1
        };

        const removeItem = jest.fn();
        render(<BasketItem item={item} updateQuantity={() => {}} removeItem={removeItem} />);
        const removeButton = screen.getByTestId('remove-item-1');
        fireEvent.click(removeButton);

        expect(removeItem).toHaveBeenCalledWith(1);
    });

    it('decrements quantity', () => {
        const item: BasketItemType = {
        id: 1,
        name: 'Apple',
        description: 'A sweet, juicy treat!',
        price: 1.99,
        quantity: 5
        };

        const updateQuantity = jest.fn();
        render(<BasketItem item={item} updateQuantity={updateQuantity} removeItem={() => {}} />);
        const decrementButton = screen.getByTestId('decrement-item-1');
        fireEvent.click(decrementButton);

        expect(updateQuantity).toHaveBeenCalledWith(1, 4);
    });

    it('increments quantity', () => {
        const item: BasketItemType = {
        id: 1,
        name: 'Apple',
        description: 'A sweet, juicy treat!',
        price: 1.99,
        quantity: 5
        };

        const updateQuantity = jest.fn();
        render(<BasketItem item={item} updateQuantity={updateQuantity} removeItem={() => {}} />);
        const incrementButton = screen.getByTestId('increment-item-1');
        fireEvent.click(incrementButton);

        expect(updateQuantity).toHaveBeenCalledWith(1, 6);
    });

    it('does not allow non-numeric input', () => {
        const item: BasketItemType = {
        id: 1,
        name: 'Apple',
        description: 'A sweet, juicy treat!',
        price: 1.99,
        quantity: 1
        };

        const updateQuantity = jest.fn();
        render(<BasketItem item={item} updateQuantity={updateQuantity} removeItem={() => {}} />);
        const quantityInput = screen.getByTestId('quantity-item-1');
        fireEvent.change(quantityInput, { target: { value: 'a' } });

        expect(updateQuantity).not.toHaveBeenCalled();
    });
});
