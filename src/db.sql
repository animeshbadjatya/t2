
INSERT INTO "ecommerce"."public"."product" (sku, name, description, image_url, active, units_in_stock,
unit_price, date_created)
VALUES ('BOOK-TECH-1001', 'Spring Framework Tutorial', 'Learn Spring',
'assets/images/products/placeholder.png'
,true,100,29.99, NOW());

INSERT INTO "ecommerce"."public"."product" (sku, name, description, image_url, active, units_in_stock,
unit_price, date_created)
VALUES ('BOOK-TECH-1002', 'Kubernetes - Deploying Containers', 'Learn Kubernetes',
'assets/images/products/placeholder.png'
,true,100,24.99, NOW());

INSERT INTO "ecommerce"."public"."product" (sku, name, description, image_url, active, units_in_stock,
unit_price, date_created)
VALUES ('BOOK-TECH-1003', 'Internet of Things (IoT) - Getting Started', 'Learn IoT',
'assets/images/products/placeholder.png'
,true,100,29.99, NOW());

INSERT INTO "ecommerce"."public"."product" (sku, name, description, image_url, active, units_in_stock,
unit_price, date_created)
VALUES ('BOOK-TECH-1004', 'The Go Programming Language: A to Z', 'Learn Go',
'assets/images/products/placeholder.png'
,true,100,24.99, NOW());

--API JSON body for product save - {
--                           "sku": "BOOK_TEST",
--                           "name": "TEST",
--                           "description" : "TEST",
--                           "image_url" : "assets/images/products/test.png",
--                           "active" : "true",
--                           "units_in_stock" : "2",
--                           "unit_price" : "01.0"
--                       }