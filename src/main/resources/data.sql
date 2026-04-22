-- ==============================================
-- H2 Database Initial Data
-- Converted from Supabase migration files
-- ==============================================

-- 初始化菜单数据
INSERT INTO menu_items (
  item_id, category, name, description, base_price, price_label, available, customization_options
)
VALUES
  ('BREAKFAST-SPECIAL', 'Breakfast Specials', 'Breakfast Special', 'Bagel, egg, American cheese, and your choice of topping. Availability varies.', 7.95, '$7.95', true, '[]'),
  ('WRAP-STANDARD', 'Breakfast Wraps', 'Standard Wrap', 'Bacon, egg, and cheddar cheese in a wrap.', 7.95, '$7.95', true, '[]'),
  ('WRAP-PEANUT-BUTTER-CRUNCH', 'Breakfast Wraps', 'Peanut Butter Crunch Wrap', 'Peanut butter, grilled banana, honey, and house-made granola.', 7.95, '$7.95', true, '[]'),
  ('WRAP-SOUTHWESTERN', 'Breakfast Wraps', 'Southwestern Wrap', 'Black beans, corn, red peppers, tomato, cheddar cheese, egg, and scallions.', 7.95, '$7.95', true, '[]'),
  ('BAGEL-PLAIN', 'Signature Bagels', 'Plain Bagel', 'Fresh-made classic bagel sold as-is or topped with your choice of spread.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-WHOLE-WHEAT', 'Signature Bagels', 'Whole Wheat Bagel', 'Fresh-made whole wheat bagel.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-EVERYTHING', 'Signature Bagels', 'Everything Bagel', 'Fresh-made everything bagel with sesame, garlic, onion, and salt.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-SESAME', 'Signature Bagels', 'Sesame Bagel', 'Fresh-made sesame bagel.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-POPPY', 'Signature Bagels', 'Poppy Bagel', 'Fresh-made poppy seed bagel.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-ONION', 'Signature Bagels', 'Onion Bagel', 'Fresh-made onion bagel.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-SALT', 'Signature Bagels', 'Salt Bagel', 'Fresh-made salt bagel.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-BLUEBERRY', 'Signature Bagels', 'Blueberry Bagel', 'Fresh-made blueberry bagel.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-CINNAMON-RAISIN', 'Signature Bagels', 'Cinnamon Raisin Bagel', 'Fresh-made cinnamon raisin bagel.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-ASIAGO', 'Signature Bagels', 'Asiago Bagel', 'Fresh-made asiago bagel.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-GLUTEN-FREE', 'Signature Bagels', 'Gluten-Free Bagel', 'Not prepared in a gluten-free kitchen.', 1.50, '$1.50 each', true, '[]'),
  ('BAGEL-HALF-DOZEN', 'Signature Bagels', 'Bagels Half-Dozen', 'Six fresh-made bagels. Availability varies.', 9.00, '$9.00', true, '[]'),
  ('BAGEL-DOZEN', 'Signature Bagels', 'Bagels Dozen', 'Twelve fresh-made bagels. Availability varies.', 15.00, '$15.00', true, '[]'),
  ('SPREAD-PLAIN-CC', 'Spreads', 'Plain Cream Cheese', 'Classic cream cheese spread.', 4.50, '$4.50', true, '[]'),
  ('SPREAD-VEGGIE-CC', 'Spreads', 'Veggie Cream Cheese', 'Vegetable cream cheese spread.', 4.75, '$4.75', true, '[]'),
  ('SPREAD-GARLIC-HERB-CC', 'Spreads', 'Garlic & Herb Cream Cheese', 'Garlic and herb cream cheese spread.', 4.75, '$4.75', true, '[]'),
  ('SPREAD-LOX', 'Spreads', 'Lox Spread', 'House lox spread.', 5.25, '$5.25', true, '[]'),
  ('SPREAD-BERRY-BERRY', 'Spreads', 'Berry Berry Cream Cheese', 'Sweet berry cream cheese spread.', 4.75, '$4.75', true, '[]'),
  ('SPREAD-BUTTER', 'Spreads', 'Butter', 'Classic butter topping.', 3.25, '$3.25', true, '[]'),
  ('SPREAD-HONEY', 'Spreads', 'Honey', 'Sweet honey topping.', 3.25, '$3.25', true, '[]'),
  ('SPREAD-JELLY', 'Spreads', 'Jelly', 'Fruit jelly topping.', 3.25, '$3.25', true, '[]'),
  ('SPREAD-PEANUT-BUTTER', 'Spreads', 'Peanut Butter', 'Peanut butter topping.', 4.25, '$4.25', true, '[]'),
  ('SPREAD-CINNAMON-TOAST', 'Spreads', 'Cinnamon Toast', 'Sweet cinnamon toast topping.', 3.75, '$3.75', true, '[]'),
  ('SMOOTHIE-SOB', 'Smoothies', 'S.O.B.', 'Strawberry, orange juice, banana.', 7.95, '$7.95', true, '[]'),
  ('SMOOTHIE-JOE-PAPAYA', 'Smoothies', 'Joe Papaya', 'Coconut, strawberry, mango, pineapple.', 7.95, '$7.95', true, '[]'),
  ('SMOOTHIE-LAGUNA-PEACH', 'Smoothies', 'Laguna Peach', 'Creamy combination of peach, strawberry, apple juice.', 7.95, '$7.95', true, '[]'),
  ('SMOOTHIE-GOING-GREEN', 'Smoothies', 'Going Green', 'Banana, mango, spinach, lemonade, coconut.', 7.95, '$7.95', true, '[]'),
  ('SMOOTHIE-VERY-BERRY', 'Smoothies', 'Very Berry', 'Strawberry, blueberry, raspberry, apple juice.', 7.95, '$7.95', true, '[]'),
  ('SHAKE-CHOCOLATE', 'Shakes', 'Chocolate Shake', 'Ice cream and chocolate.', 7.95, '$7.95', true, '[]'),
  ('SHAKE-VANILLA', 'Shakes', 'Vanilla Shake', 'French vanilla blended into ice cream.', 7.95, '$7.95', true, '[]'),
  ('SHAKE-STRAWBERRY', 'Shakes', 'Strawberry Shake', 'Sweet strawberries combined with rich ice cream.', 7.95, '$7.95', true, '[]'),
  ('SHAKE-MOCHA-MAMA', 'Shakes', 'Mocha Mama', 'A chocolate and espresso love affair.', 7.95, '$7.95', true, '[]'),
  ('SHAKE-CHAI', 'Shakes', 'Chai Shake', 'Exotic chai tea blended into a creamy shake.', 7.95, '$7.95', true, '[]'),
  ('OATMEAL-BANANA', 'Oatmeal', 'Banana, Brown Sugar & Cinnamon Oatmeal', 'Steel-cut oats topped with fresh bananas, brown sugar, and cinnamon.', 7.95, '$7.95', true, '[]'),
  ('SANDWICH-BAJA-CHICKEN-CLUB', 'Sandwiches', 'Baja Chicken Club', 'Grilled chicken breast with cheddar cheese, spicy mayo, avocado, bacon, lettuce, tomato, and red onion.', 12.95, '$12.95', true, '[]'),
  ('SANDWICH-GRILLED-CHICKEN-PESTO', 'Sandwiches', 'Grilled Chicken Pesto', 'Grilled chicken breast with house-made pesto mayo, lettuce, tomato, and red onion.', 10.95, '$10.95', true, '[]'),
  ('SANDWICH-CARVED-TURKEY-CLUB', 'Sandwiches', 'Carved Turkey Club', 'Roasted turkey breast with bacon, mayo, lettuce, tomato, and red onion.', 10.95, '$10.95', true, '[]'),
  ('SANDWICH-BLT', 'Sandwiches', 'BLT', 'Crisp bacon, lettuce, and tomato with mayo.', 9.95, '$9.95', true, '[]'),
  ('SANDWICH-PIZZA-BAGEL', 'Sandwiches', 'Pizza Bagel', 'Pizza perfection on your choice of bagel.', 6.95, '$6.95', true, '[]'),
  ('SANDWICH-CHICKPEA-SALAD', 'Sandwiches', 'Chickpea Salad Sandwich', 'Bright, lemony smashed chickpea salad topped with lettuce and cucumber.', 10.95, '$10.95', true, '[]'),
  ('SANDWICH-LOX-CREAM-CHEESE', 'Sandwiches', 'Lox & Cream Cheese', 'Nova lox and plain cream cheese with tomato, red onion, cucumber, and capers on the bagel of your choice.', 11.95, '$11.95', true, '[]'),
  ('HOT-COFFEE', 'Coffees & Teas', 'Hot Coffee', 'Hot brewed coffee.', 2.65, '$2.65 / $2.95 / $3.25', true, '[]'),
  ('HOT-TEA', 'Coffees & Teas', 'Hot Tea', 'Hot tea.', 2.65, '$2.65 / $2.95 / $3.25', true, '[]'),
  ('HOT-ESPRESSO', 'Coffees & Teas', 'Espresso', 'Single espresso shot.', 3.50, '$3.50', true, '[]'),
  ('HOT-CAFE-LATTE', 'Coffees & Teas', 'Cafe Latte', 'Hot cafe latte.', 4.95, '$4.95 / $5.45 / $5.95', true, '[]'),
  ('HOT-CAPPUCCINO', 'Coffees & Teas', 'Cappuccino', 'Hot cappuccino.', 4.95, '$4.95 / $5.45 / $5.95', true, '[]'),
  ('HOT-AMERICANO', 'Coffees & Teas', 'Americano', 'Hot americano.', 4.45, '$4.45 / $4.95 / $5.45', true, '[]'),
  ('HOT-CAFE-MOCHA', 'Coffees & Teas', 'Cafe Mocha', 'Hot cafe mocha.', 5.45, '$5.45 / $5.95 / $6.45', true, '[]'),
  ('HOT-CARAMEL-LATTE', 'Coffees & Teas', 'Caramel Latte', 'Hot caramel latte.', 5.45, '$5.45 / $5.95 / $6.45', true, '[]'),
  ('HOT-CHOCOLATE', 'Coffees & Teas', 'Hot Chocolate', 'Hot chocolate.', 4.25, '$4.25 / $4.50 / $5.25', true, '[]'),
  ('HOT-CHAI-LATTE', 'Coffees & Teas', 'Chai Latte', 'Hot chai latte.', 4.95, '$4.95 / $5.45 / $5.95', true, '[]'),
  ('HOT-STEAMED-MILK', 'Coffees & Teas', 'Steamed Milk', 'Steamed milk.', 3.25, '$3.25 / $3.75 / $4.25', true, '[]'),
  ('COLD-COLD-BREW', 'Coffees & Teas', 'Cold Brew', 'Cold brew coffee.', 4.75, '$4.75 / $5.25 / $5.75', true, '[]'),
  ('COLD-ICED-TEA', 'Coffees & Teas', 'Iced Tea', 'Iced tea.', 2.75, '$2.75 / $3.25 / $3.75', true, '[]'),
  ('COLD-FOUNTAIN-SODA', 'Coffees & Teas', 'Fountain Soda', 'Fountain soda.', 2.75, '$2.75 / $3.25 / $3.75', true, '[]'),
  ('COLD-ICED-LATTE', 'Coffees & Teas', 'Iced Latte', 'Iced latte.', 4.95, '$4.95 / $5.65 / $6.40', true, '[]'),
  ('COLD-ICED-AMERICANO', 'Coffees & Teas', 'Iced Americano', 'Iced americano.', 4.50, '$4.50 / $5.45 / $5.95', true, '[]'),
  ('COLD-ICED-MOCHA', 'Coffees & Teas', 'Iced Mocha', 'Iced mocha.', 5.25, '$5.25 / $5.95 / $6.75', true, '[]'),
  ('COLD-ICED-CARAMEL-LATTE', 'Coffees & Teas', 'Iced Caramel Latte', 'Iced caramel latte.', 5.25, '$5.25 / $5.95 / $6.75', true, '[]'),
  ('COLD-ICED-CHAI', 'Coffees & Teas', 'Iced Chai', 'Iced chai.', 5.25, '$5.25 / $5.95 / $6.75', true, '[]')
ON CONFLICT (item_id) DO UPDATE SET
  category = excluded.category,
  name = excluded.name,
  description = excluded.description,
  base_price = excluded.base_price,
  price_label = excluded.price_label,
  available = excluded.available,
  customization_options = excluded.customization_options,
  updated_at = CURRENT_TIMESTAMP;

-- 初始化积分奖励数据
INSERT INTO rewards (
  id, name, points_required, description, cart_item_id
)
VALUES
  ('free-coffee', 'Free Hot or Iced Coffee', 50, 'Redeem for a brewed coffee or iced tea size of your choice.', 'REWARD-FREE-COFFEE'),
  ('free-bagel', 'Free Bagel', 75, 'Pick any classic Irving''s bagel once you unlock it.', 'REWARD-FREE-BAGEL'),
  ('free-drink-or-bagel', 'Free Drink or Bagel', 100, 'Use on a favorite drink or bagel at the full reward tier.', 'REWARD-FREE-DRINK-OR-BAGEL')
ON CONFLICT (id) DO UPDATE SET
  name = excluded.name,
  points_required = excluded.points_required,
  description = excluded.description,
  cart_item_id = excluded.cart_item_id,
  updated_at = CURRENT_TIMESTAMP;