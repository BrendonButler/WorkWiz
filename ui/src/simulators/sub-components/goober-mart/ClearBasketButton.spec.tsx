import { ClearBasketButton } from './ClearBasketButton';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';

describe('ClearBasketButton', () => {
  it('renders', () => {
    render(<ClearBasketButton onClearBasket={() => {}} />);
    const clearBasketButton = screen.getByTestId('empty-basket-button');

    expect(clearBasketButton).toBeInTheDocument();
  });

  it('shows cancel and clear buttons when clicking the Empty Basket button', async () => {
    render(<ClearBasketButton onClearBasket={() => {}} />);
    const clearBasketButton = screen.getByTestId('empty-basket-button');
    fireEvent.click(clearBasketButton);

    const cancelButton = await screen.findByTestId('clear-basket-cancel-button');
    const clearButton = await screen.findByTestId('clear-basket-button');

    expect(cancelButton).toBeInTheDocument();
    expect(clearButton).toBeInTheDocument();
  });

  it('cancels clearing the basket', async () => {
    render(<ClearBasketButton onClearBasket={() => {}} />);
    const clearBasketButton = screen.getByTestId('empty-basket-button');
    fireEvent.click(clearBasketButton);

    const cancelButton = await screen.findByTestId('clear-basket-cancel-button');
    fireEvent.click(cancelButton);

    await waitFor(() => {
      expect(screen.queryByTestId('clear-basket-cancel-button')).not.toBeInTheDocument();
    });
  });

  it('clears the basket', async () => {
    const onClearBasket = jest.fn();
    render(<ClearBasketButton onClearBasket={onClearBasket} />);
    const clearBasketButton = screen.getByTestId('empty-basket-button');
    fireEvent.click(clearBasketButton);

    const clearButton = await screen.findByTestId('clear-basket-button');
    fireEvent.click(clearButton);

    await waitFor(() => {
      expect(onClearBasket).toHaveBeenCalled();
    });
  });
});
