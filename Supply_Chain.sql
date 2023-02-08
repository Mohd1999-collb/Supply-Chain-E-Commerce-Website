/* Create the Supply Chain database */
create database Supply_Chain;
use supply_chain;
show tables;

/* Create the user table */
create table User(
	emailId varchar(255) primary key,
    userName varchar(255),
    userPassword varchar(255),
    userType varchar(255)    
);

select * from User;

/* Insert the data into the user table */
insert into user (emailId, userName, userPassword, userType)
values 
	("talib123@gmail.com", "Mohd Talib", "12345", "Buyer"),
    ("sunaina3242@gamil.com", "Sunaina Omar", "1000", "Buyer"),
    ("shubham4567@gmail.com", "Shubham Jauhari", "7860", "Seller"),
    ("abhishek1000@gamil.com", "Abhishek Mishra", "9999", "Seller");
  select * from user;  
/* Create the product table*/
create table Product(
	productId int primary key,
    productName varchar(255),
    productPrice float,
    sellerId varchar(255),
    foreign key (sellerId) references user(emailId)
);

show tables;
/* Insert the data into the product table */
insert into Product (productId, productName, productPrice, sellerId)
values
	(1, "Car", 100000, "shubham4567@gmail.com"),
    (2, "Thar", 150000, "abhishek1000@gamil.com");
    
select * from product;

/* Create the order table*/
create table orders(
	orderId int primary key,
    productName varchar(255),
    productId int,
    userId varchar(255),
    foreign key(productId) references product(productId),
    foreign key(userId) references user(emailId)
);

select * from orders;



