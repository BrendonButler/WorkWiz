import { SearchResults } from './SearchResults';
import { fireEvent, render, screen, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';

describe('SearchResults', () => {
  it('does not render without items', () => {
    render(
      <SearchResults
        searchResults={[]}
        addToBasket={() => {}}
        hasMore={false}
        loadMoreItems={() => {}}
        isLoading={false}
      />
    );

    const searchResults = screen.queryByTestId('search-results');

    expect(searchResults).not.toBeInTheDocument();
  });

  it('renders items', () => {
    const searchResults = [
      { id: 1, name: 'Apple', description: 'A sweet, juicy treat!', price: 1.99 },
      { id: 2, name: 'Banana', description: 'A firm, yellow fruit!', price: 0.99 }
    ];

    render(
      <SearchResults
        searchResults={searchResults}
        addToBasket={() => {}}
        hasMore={false}
        loadMoreItems={() => {}}
        isLoading={false}
      />
    );

    const searchResult1 = screen.getByTestId('search-result-0');
    const searchResult2 = screen.getByTestId('search-result-1');

    expect(searchResult1).toBeInTheDocument();
    expect(searchResult2).toBeInTheDocument();
  });
});
