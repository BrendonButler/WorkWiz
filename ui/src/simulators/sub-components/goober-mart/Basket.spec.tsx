import { Basket } from '@/simulators/sub-components/goober-mart/Basket';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';

describe('Basket', () => {
  it('renders', () => {
    render(
      <Basket basket={[]} updateQuantity={() => {}} removeItem={() => {}} emptyBasket={() => {}} />
    );

    const shoppingBasket = screen.getByTestId('shopping-basket');

    expect(shoppingBasket).toBeInTheDocument();
  });

  it('renders items', () => {
    const items = [
      { id: 1, name: 'Apple', description: 'A sweet, juicy treat!', price: 1.99, quantity: 1 },
      { id: 2, name: 'Banana', description: 'A firm, yellow fruit!', price: 0.99, quantity: 2 }
    ];

    render(
      <Basket
        basket={items}
        updateQuantity={() => {}}
        removeItem={() => {}}
        emptyBasket={() => {}}
      />
    );

    const basketItem1 = screen.getByTestId('basket-item-1');
    const basketItem2 = screen.getByTestId('basket-item-2');

    expect(basketItem1).toBeInTheDocument();
    expect(basketItem2).toBeInTheDocument();
  });

  it('updates quantity', () => {
    const items = [
      { id: 1, name: 'Apple', description: 'A sweet, juicy treat!', price: 1.99, quantity: 1 }
    ];

    const updateQuantity = jest.fn();
    render(
      <Basket
        basket={items}
        updateQuantity={updateQuantity}
        removeItem={() => {}}
        emptyBasket={() => {}}
      />
    );

    const quantityInput = screen.getByTestId('quantity-item-1');
    fireEvent.change(quantityInput, { target: { value: '5' } });

    expect(updateQuantity).toHaveBeenCalledWith(1, 5);
  });

  it('removes item', () => {
    const items = [
      { id: 1, name: 'Apple', description: 'A sweet, juicy treat!', price: 1.99, quantity: 1 }
    ];

    const removeItem = jest.fn();
    render(
      <Basket
        basket={items}
        updateQuantity={() => {}}
        removeItem={removeItem}
        emptyBasket={() => {}}
      />
    );

    const removeButton = screen.getByTestId('remove-item-1');
    fireEvent.click(removeButton);

    expect(removeItem).toHaveBeenCalledWith(1);
  });

  it('empties basket', async () => {
    const items = [
      { id: 1, name: 'Apple', description: 'A sweet, juicy treat!', price: 1.99, quantity: 1 }
    ];

    const emptyBasket = jest.fn();
    render(
      <Basket
        basket={items}
        updateQuantity={() => {}}
        removeItem={() => {}}
        emptyBasket={emptyBasket}
      />
    );

    const emptyButton = screen.getByTestId('empty-basket-button');
    fireEvent.click(emptyButton);

    const clearButton = await screen.findByTestId('clear-basket-button');
    fireEvent.click(clearButton);

    await waitFor(() => {
      expect(screen.queryByTestId('basket-item-0')).not.toBeInTheDocument();
    });
  });

  it('cancels emptying basket', async () => {
    const items = [
      { id: 1, name: 'Apple', description: 'A sweet, juicy treat!', price: 1.99, quantity: 1 }
    ];

    const emptyBasket = jest.fn();
    render(
      <Basket
        basket={items}
        updateQuantity={() => {}}
        removeItem={() => {}}
        emptyBasket={emptyBasket}
      />
    );

    const emptyButton = screen.getByTestId('empty-basket-button');
    fireEvent.click(emptyButton);

    const cancelButton = await screen.findByTestId('clear-basket-cancel-button');
    fireEvent.click(cancelButton);

    await waitFor(() => {
      expect(screen.queryByTestId('basket-item-1')).toBeInTheDocument();
    });
  });
});
