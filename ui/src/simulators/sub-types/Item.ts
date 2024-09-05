export interface ShoppingItem {
  id: number;
  name: string;
  description: string;
  price: number;
}

export interface BasketItem extends ShoppingItem {
  quantity: number;
}
