-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2024 at 07:47 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `backend_java`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `account_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(45) NOT NULL,
  `account_status` tinyint(1) NOT NULL,
  `position` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`account_id`, `username`, `password`, `account_status`, `position`) VALUES
(1, 'meohuy', '123456', 1, 'staff'),
(2, 'minhlq', '123456', 1, 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `bill_id` int(11) NOT NULL,
  `staff_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `total_price` int(11) NOT NULL,
  `address` varchar(45) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `discount_code` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`bill_id`, `staff_id`, `date`, `total_price`, `address`, `customer_id`, `discount_code`) VALUES
(1, 1, '2024-04-03', 1500000, '120 Âu Cơ', 1, NULL),
(2, 1, '2024-04-06', 2600000, '243 An Dương Vương', 2, NULL),
(3, 2, '2024-11-20', 4100000, '1 Lê Duẩn', 3, NULL),
(4, 2, '2024-11-11', 2550000, '1 Trường Chinh', 4, NULL),
(5, 2, '2024-04-14', 1500000, '20 Lê Đại Hành', 1, NULL),
(6, 1, '2024-04-14', 2600000, '40 Lý Thái Tổ', 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `bills_details`
--

CREATE TABLE `bills_details` (
  `product_serial_id` int(11) NOT NULL,
  `bill_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price_single` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bills_details`
--

INSERT INTO `bills_details` (`product_serial_id`, `bill_id`, `quantity`, `price_single`) VALUES
(7, 1, 1, 800000),
(9, 1, 1, 700000),
(4, 2, 1, 1100000),
(5, 2, 1, 1500000),
(7, 3, 1, 800000),
(9, 3, 1, 700000),
(4, 3, 1, 1100000),
(5, 3, 1, 1500000),
(1, 4, 1, 1850000),
(9, 4, 1, 700000);

-- --------------------------------------------------------

--
-- Table structure for table `brands`
--

CREATE TABLE `brands` (
  `brand_id` int(11) NOT NULL,
  `brand_name` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brands`
--

INSERT INTO `brands` (`brand_id`, `brand_name`, `status`) VALUES
(1, 'Adidas', 1),
(2, 'Nike', 1),
(3, 'Converse', 1);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`, `status`) VALUES
(1, 'Chạy bộ', 1),
(2, 'Đá banh', 1),
(3, 'Leo núi', 1);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(45) NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `customer_name`, `phone_number`, `status`) VALUES
(1, 'minhtri', '909080823', 1),
(2, 'anhthu', '808090932', 1),
(3, 'kimyen', '123456256', 1),
(4, 'vannam', '341565224', 1);

-- --------------------------------------------------------

--
-- Table structure for table `discounts`
--

CREATE TABLE `discounts` (
  `discount_code` varchar(45) NOT NULL,
  `discount_value` int(11) NOT NULL,
  `type` varchar(2) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `discounts`
--

INSERT INTO `discounts` (`discount_code`, `discount_value`, `type`, `start_date`, `end_date`, `status`) VALUES
('GIAMNUAGIA', 50, 'PR', '2024-04-01', '2025-04-30', 1),
('SALE50K', 50000, 'AR', '2024-04-01', '2025-04-30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `goodsreceipts`
--

CREATE TABLE `goodsreceipts` (
  `receipt_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `total_price` int(11) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `staff_id` int(11) NOT NULL,
  `status` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `goodsreceipts`
--

INSERT INTO `goodsreceipts` (`receipt_id`, `date`, `total_price`, `supplier_id`, `staff_id`, `status`) VALUES
(1, '2024-01-01', 5400000, 1, 1, 1),
(2, '2024-01-01', 6000000, 2, 2, 1),
(3, '2024-02-01', 4800000, 3, 1, 1),
(4, '2024-01-01', 32000000, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `goodsreceipt_details`
--

CREATE TABLE `goodsreceipt_details` (
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `receipt_id` int(11) NOT NULL,
  `input_price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `goodsreceipt_details`
--

INSERT INTO `goodsreceipt_details` (`product_id`, `quantity`, `receipt_id`, `input_price`) VALUES
(1, 3, 1, 1480000),
(2, 3, 2, 560000),
(3, 4, 3, 1600000),
(7, 10, 4, 500000),
(9, 10, 4, 500000),
(5, 10, 4, 1200000),
(4, 10, 4, 1000000);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `output_price` int(11) NOT NULL,
  `country` varchar(45) NOT NULL,
  `year_of_product` int(11) NOT NULL,
  `discount_percent` int(11) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `category_id`, `brand_id`, `product_name`, `output_price`, `country`, `year_of_product`, `discount_percent`, `image_path`, `quantity`, `status`) VALUES
(1, 1, 1, 'Air Jordan 1', 1850000, 'USA', 1985, 10, '', 2, 1),
(2, 1, 1, 'Air Jordan 4          ', 2200000, 'USA', 1989, 15, '', 3, 1),
(3, 1, 2, 'Nike Air Force 1      ', 1685000, 'VietNam', 1982, 10, '', 4, 1),
(4, 2, 2, 'Nike Mercurial        ', 1100000, 'VietNam', 2022, 5, '', 8, 1),
(5, 2, 3, 'Adidas Predator       ', 1500000, 'Germany  ', 1994, 10, '', 8, 1),
(6, 2, 3, 'Adidas Ultra Boost    ', 1800000, 'ChiNa', 2015, 15, '', 0, 1),
(7, 3, 1, 'Converse Chuck Taylor ', 800000, 'USA', 1917, 5, '', 8, 1),
(8, 3, 1, 'Converse Jack Purcell ', 900000, 'USA      ', 1935, 5, '', 0, 1),
(9, 3, 2, 'Puma Suede            ', 700000, 'Germany  ', 1968, 5, '', 7, 1),
(10, 3, 3, 'The North Face        ', 3500000, 'USA      ', 1966, 15, '', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product_details`
--

CREATE TABLE `product_details` (
  `product_serial_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_details`
--

INSERT INTO `product_details` (`product_serial_id`, `product_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 2),
(6, 2),
(7, 3),
(8, 3),
(9, 3),
(10, 3),
(11, 4),
(12, 4),
(13, 4),
(14, 4),
(15, 4),
(16, 4),
(17, 4),
(18, 4),
(19, 4),
(20, 4),
(41, 5),
(42, 5),
(43, 5),
(44, 5),
(45, 5),
(46, 5),
(47, 5),
(48, 5),
(49, 5),
(50, 5),
(21, 7),
(22, 7),
(23, 7),
(24, 7),
(25, 7),
(26, 7),
(27, 7),
(28, 7),
(29, 7),
(30, 7),
(31, 9),
(32, 9),
(33, 9),
(34, 9),
(35, 9),
(36, 9),
(37, 9),
(38, 9),
(39, 9),
(40, 9);

-- --------------------------------------------------------

--
-- Table structure for table `returns`
--

CREATE TABLE `returns` (
  `return_id` int(11) NOT NULL,
  `product_serial_id` int(11) NOT NULL,
  `date_return` date NOT NULL,
  `reason` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`, `status`) VALUES
(1, 'Quản lý sản phẩm', 1),
(2, 'Quản lý nhân viên', 1),
(3, 'Quản lý nhà cung cấp', 1),
(4, 'Quản lý tài khoản', 1),
(5, 'Quản lý nhập hàng', 1),
(6, 'Quản lý xuất hàng', 1),
(7, 'Quản lý phân quyền', 1),
(8, 'Quản lý thống kê', 1);

-- --------------------------------------------------------

--
-- Table structure for table `role_details`
--

CREATE TABLE `role_details` (
  `role_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role_details`
--

INSERT INTO `role_details` (`role_id`, `account_id`) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2);

-- --------------------------------------------------------

--
-- Table structure for table `staffs`
--

CREATE TABLE `staffs` (
  `staff_id` int(11) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staffs`
--

INSERT INTO `staffs` (`staff_id`, `fullname`, `email`, `phone_number`, `status`, `account_id`) VALUES
(1, 'Huy Nguyen Anh', 'huyna@sgu.edu.vn', '0909123123', 1, 1),
(2, 'Minh Lu Quang', 'minhlq2911@sgu.edu.vn', '0931814480', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `supplier_id` int(11) NOT NULL,
  `supplier_name` varchar(50) NOT NULL,
  `supplier_address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`supplier_id`, `supplier_name`, `supplier_address`) VALUES
(1, 'Nhà cung cấp 1', '200 Nguyễn Trãi'),
(2, 'Nhà cung cấp 2', '256 Lê Lợi'),
(3, 'Nhà cung cấp 3', '269 Hồn Bàng');

-- --------------------------------------------------------

--
-- Table structure for table `warranty_details`
--

CREATE TABLE `warranty_details` (
  `warranty_detail_id` int(11) NOT NULL,
  `product_serial_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `warranty_date` date NOT NULL,
  `reason` varchar(45) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `warranty_details`
--

INSERT INTO `warranty_details` (`warranty_detail_id`, `product_serial_id`, `start_date`, `end_date`, `warranty_date`, `reason`, `status`) VALUES
(1, 1, '2024-04-03', '2024-04-30', '2024-04-08', 'lỗi sản phẩm', 1),
(2, 7, '2024-11-11', '2024-12-20', '2024-11-20', 'khách hàng không hài long', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `customer_id_idx` (`customer_id`),
  ADD KEY `discoutn_code_idx` (`discount_code`),
  ADD KEY `fk_staff_id_bill` (`staff_id`);

--
-- Indexes for table `bills_details`
--
ALTER TABLE `bills_details`
  ADD KEY `billdetail_product_id` (`product_serial_id`),
  ADD KEY `billdetail_bill_id` (`bill_id`);

--
-- Indexes for table `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`brand_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `discounts`
--
ALTER TABLE `discounts`
  ADD PRIMARY KEY (`discount_code`);

--
-- Indexes for table `goodsreceipts`
--
ALTER TABLE `goodsreceipts`
  ADD PRIMARY KEY (`receipt_id`),
  ADD KEY `FK_Supplier` (`supplier_id`),
  ADD KEY `FK_Staff` (`staff_id`);

--
-- Indexes for table `goodsreceipt_details`
--
ALTER TABLE `goodsreceipt_details`
  ADD KEY `FK_PRODUCT_ID_GD` (`product_id`),
  ADD KEY `FK_RECEIPT_ID` (`receipt_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `category_id_idx` (`category_id`),
  ADD KEY `brand_id_idx` (`brand_id`);

--
-- Indexes for table `product_details`
--
ALTER TABLE `product_details`
  ADD PRIMARY KEY (`product_serial_id`),
  ADD KEY `FK_Product` (`product_id`);

--
-- Indexes for table `returns`
--
ALTER TABLE `returns`
  ADD PRIMARY KEY (`return_id`),
  ADD KEY `FK_PRODUCT_SERIAL_ID` (`product_serial_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `role_details`
--
ALTER TABLE `role_details`
  ADD KEY `fk_account_id_role_detail` (`account_id`),
  ADD KEY `fk_role_id_role_detail` (`role_id`);

--
-- Indexes for table `staffs`
--
ALTER TABLE `staffs`
  ADD PRIMARY KEY (`staff_id`),
  ADD KEY `fk_account_id_staff` (`account_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplier_id`);

--
-- Indexes for table `warranty_details`
--
ALTER TABLE `warranty_details`
  ADD PRIMARY KEY (`warranty_detail_id`),
  ADD KEY `product_id_idx` (`product_serial_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product_details`
--
ALTER TABLE `product_details`
  MODIFY `product_serial_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `staffs`
--
ALTER TABLE `staffs`
  MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supplier_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `warranty_details`
--
ALTER TABLE `warranty_details`
  MODIFY `warranty_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `fk_customer_id_bill` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_staff_id_bill` FOREIGN KEY (`staff_id`) REFERENCES `accounts` (`account_id`);

--
-- Constraints for table `bills_details`
--
ALTER TABLE `bills_details`
  ADD CONSTRAINT `fk_billdetail_bill_id` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`bill_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_billdetail_product_id` FOREIGN KEY (`product_serial_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `goodsreceipts`
--
ALTER TABLE `goodsreceipts`
  ADD CONSTRAINT `FK_Staff` FOREIGN KEY (`staff_id`) REFERENCES `staffs` (`staff_id`),
  ADD CONSTRAINT `FK_Supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`);

--
-- Constraints for table `goodsreceipt_details`
--
ALTER TABLE `goodsreceipt_details`
  ADD CONSTRAINT `FK_PRODUCT_ID_GD` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  ADD CONSTRAINT `FK_RECEIPT_ID` FOREIGN KEY (`receipt_id`) REFERENCES `goodsreceipts` (`receipt_id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FK_BRAND_ID_PRODUCTS` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`brand_id`),
  ADD CONSTRAINT `FK_CATEGORY_ID_PRODUCTS` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

--
-- Constraints for table `product_details`
--
ALTER TABLE `product_details`
  ADD CONSTRAINT `FK_Product` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table `returns`
--
ALTER TABLE `returns`
  ADD CONSTRAINT `FK_PRODUCT_SERIAL_ID` FOREIGN KEY (`product_serial_id`) REFERENCES `bills_details` (`product_serial_id`);

--
-- Constraints for table `role_details`
--
ALTER TABLE `role_details`
  ADD CONSTRAINT `FK_ACCOUNT_ID_RD` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`),
  ADD CONSTRAINT `FK_ROLE_ID_RD` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);

--
-- Constraints for table `staffs`
--
ALTER TABLE `staffs`
  ADD CONSTRAINT `FK_ACCOUNT_ID_STAFF` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`);

--
-- Constraints for table `warranty_details`
--
ALTER TABLE `warranty_details`
  ADD CONSTRAINT `product_id` FOREIGN KEY (`product_serial_id`) REFERENCES `product_details` (`product_serial_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
