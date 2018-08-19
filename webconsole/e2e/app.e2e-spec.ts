import { ArtShopPage } from './app.po';

describe('art-shop App', () => {
  let page: ArtShopPage;

  beforeEach(() => {
    page = new ArtShopPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
