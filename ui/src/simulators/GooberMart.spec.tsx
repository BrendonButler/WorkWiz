import GooberMart from '@/simulators/GooberMart';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';

describe('GooberMart', () => {
  beforeEach(() => {
    global.fetch = jest.fn(() =>
      Promise.resolve({
        ok: true,
        json: () =>
          Promise.resolve([
            {
              id: 99,
              name: 'Granny Smith Apple',
              description: 'A firm, tart apple with green skin!',
              price: 1.99
            }
          ])
      })
    ) as jest.Mock;

    render(<GooberMart />);
  });

  it('renders', () => {
    const gooberMartSearch = screen.getByTestId('goober-mart-search');
    const gooberMartSearchBox = screen.getByTestId('search-box');
    const gooberMartBasket = screen.queryByTestId('shopping-basket');

    expect(gooberMartSearch).toBeInTheDocument();
    expect(gooberMartSearchBox).toBeInTheDocument();
    expect(gooberMartBasket).not.toBeInTheDocument();
  });

  it('renders basket after adding an item', async () => {
    const searchInput = screen.getByTestId('search-input');
    fireEvent.change(searchInput, { target: { value: 'apple' } });

    const firstResult = await screen.findByTestId('search-result-0');
    fireEvent.click(firstResult);

    const gooberMartBasket = await screen.findByTestId('shopping-basket');
    expect(gooberMartBasket).toBeInTheDocument();
  });

  it('no longer renders basket after removing all items', async () => {
    const searchInput = screen.getByTestId('search-input');
    fireEvent.change(searchInput, { target: { value: 'apple' } });

    const firstResult = await screen.findByTestId('search-result-0');
    fireEvent.click(firstResult);

    const gooberMartBasket = await screen.findByTestId('shopping-basket');
    expect(gooberMartBasket).toBeInTheDocument();

    const emptyBasketButton = await screen.findByTestId('empty-basket-button');
    fireEvent.click(emptyBasketButton);

    const clearBasketButton = await screen.findByTestId('clear-basket-button');
    fireEvent.click(clearBasketButton);

    const searchBox = await screen.findByTestId('search-box');
    expect(searchBox).toBeInTheDocument();

    await waitFor(() => {
      expect(screen.queryByTestId('shopping-basket')).not.toBeInTheDocument();
    });
  });

  it('continues to render basket after cancelling remove all items', async () => {
    const searchInput = screen.getByTestId('search-input');
    fireEvent.change(searchInput, { target: { value: 'apple' } });

    const firstResult = await screen.findByTestId('search-result-0');
    fireEvent.click(firstResult);

    const gooberMartBasket = await screen.findByTestId('shopping-basket');
    expect(gooberMartBasket).toBeInTheDocument();

    const emptyBasketButton = await screen.findByTestId('empty-basket-button');
    fireEvent.click(emptyBasketButton);

    const cancelButton = await screen.findByTestId('clear-basket-cancel-button');
    fireEvent.click(cancelButton);

    await waitFor(() => {
      expect(screen.queryByTestId('shopping-basket')).toBeInTheDocument();
    });
  });
});
