import { SearchBox } from './SearchBox';
import { ShoppingItem } from '@/simulators/sub-types/Item';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';

describe('SearchBox', () => {
  it('renders', () => {
    render(
      <SearchBox
        input={''}
        setInput={() => {}}
        searchResults={[]}
        addToBasket={() => {}}
        handleKeyPress={() => {}}
        hasMore={false}
        loadMoreItems={() => {}}
        isLoading={false}
      />
    );

    const searchBox = screen.getByTestId('search-box');
    const searchInput = screen.getByTestId('search-input');

    expect(searchBox).toBeInTheDocument();
    expect(searchInput).toBeInTheDocument();
  });

  it('updates input', () => {
    const setInput = jest.fn();
    render(
      <SearchBox
        input={''}
        setInput={setInput}
        searchResults={[]}
        addToBasket={() => {}}
        handleKeyPress={() => {}}
        hasMore={false}
        loadMoreItems={() => {}}
        isLoading={false}
      />
    );

    const searchInput = screen.getByTestId('search-input');
    fireEvent.change(searchInput, { target: { value: 'apple' } });

    expect(setInput).toHaveBeenCalledWith('apple');
  });

  it('searches for items', () => {
    const searchResults: ShoppingItem[] = [
      { id: 1, name: 'Apple', description: 'A sweet, juicy treat!', price: 1.99 }
    ];

    const addToBasket = jest.fn();
    render(
      <SearchBox
        input={'apple'}
        setInput={() => {}}
        searchResults={searchResults}
        addToBasket={addToBasket}
        handleKeyPress={() => {}}
        hasMore={false}
        loadMoreItems={() => {}}
        isLoading={false}
      />
    );

    const firstResult = screen.getByTestId('search-result-0');
    fireEvent.click(firstResult);

    expect(addToBasket).toHaveBeenCalledWith(searchResults[0]);
  });

  it('shows loading text', () => {
    render(
      <SearchBox
        input={''}
        setInput={() => {}}
        searchResults={[]}
        addToBasket={() => {}}
        handleKeyPress={() => {}}
        hasMore={false}
        loadMoreItems={() => {}}
        isLoading={true}
      />
    );

    const loadingText = screen.getByText('Loading...');
    expect(loadingText).toBeInTheDocument();
  });

  it('shows no results component', () => {
    render(
      <SearchBox
        input={''}
        setInput={() => {}}
        searchResults={[]}
        addToBasket={() => {}}
        handleKeyPress={() => {}}
        hasMore={false}
        loadMoreItems={() => {}}
        isLoading={false}
      />
    );

    const results = screen.queryByTestId('search-results');
    expect(results).not.toBeInTheDocument();
  });
});
