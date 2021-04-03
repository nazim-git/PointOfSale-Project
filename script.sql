USE [master]
GO
/****** Object:  Database [PointofSaleJAVA]    Script Date: 03/04/2021 11:09:40 pm ******/
CREATE DATABASE [PointofSaleJAVA]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PointofSaleJAVA', FILENAME = N'C:\Installations\SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\PointofSaleJAVA.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PointofSaleJAVA_log', FILENAME = N'C:\Installations\SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\PointofSaleJAVA_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [PointofSaleJAVA] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PointofSaleJAVA].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PointofSaleJAVA] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET ARITHABORT OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PointofSaleJAVA] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PointofSaleJAVA] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PointofSaleJAVA] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PointofSaleJAVA] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET RECOVERY FULL 
GO
ALTER DATABASE [PointofSaleJAVA] SET  MULTI_USER 
GO
ALTER DATABASE [PointofSaleJAVA] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PointofSaleJAVA] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PointofSaleJAVA] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PointofSaleJAVA] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PointofSaleJAVA] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'PointofSaleJAVA', N'ON'
GO
ALTER DATABASE [PointofSaleJAVA] SET QUERY_STORE = OFF
GO
USE [PointofSaleJAVA]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [PointofSaleJAVA]
GO
/****** Object:  Table [dbo].[Customers]    Script Date: 03/04/2021 11:09:41 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NULL,
	[phone] [varchar](70) NULL,
	[street] [varchar](100) NULL,
	[area] [varchar](150) NULL,
	[city] [varchar](150) NULL,
	[createdBy] [varchar](100) NULL,
	[createdAt] [datetime] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [varchar](100) NULL,
	[deletedAt] [datetime] NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 03/04/2021 11:09:41 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](100) NOT NULL,
	[description] [varchar](100) NULL,
	[category] [varchar](100) NULL,
	[unit] [varchar](50) NULL,
	[salePrice] [decimal](18, 2) NOT NULL,
	[purchasePrice] [decimal](18, 2) NULL,
	[stock] [int] NULL,
	[createdBy] [varchar](100) NULL,
	[createdAt] [datetime] NULL,
	[status] [bit] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [varchar](100) NULL,
	[deletedAt] [datetime] NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RunningNumber]    Script Date: 03/04/2021 11:09:41 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RunningNumber](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[type] [varchar](100) NULL,
	[number] [int] NULL,
	[prefix] [varchar](50) NULL,
 CONSTRAINT [PK_RunningNumber] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 03/04/2021 11:09:41 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](max) NOT NULL,
	[username] [varchar](max) NOT NULL,
	[password] [varchar](max) NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Customers] ON 

INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (12, N'customer 1', N'123456', N'1', N'ABC', N'XYZ', N'Danish', CAST(N'2021-03-29T23:36:42.150' AS DateTime), 0, NULL, NULL)
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (13, N'Customer 2', N'1234567', N'2', N'ABC', N'XYZ', N'Danish', CAST(N'2021-03-29T23:38:26.323' AS DateTime), 1, NULL, NULL)
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (14, N'customer 2', N'1234567', N'2', N'ABCD', N'XYZZ', N'Danish', CAST(N'2021-03-31T22:57:49.243' AS DateTime), 0, NULL, NULL)
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (15, N'customer 3', N'12345', N'3', N'ABC', N'XYZ', N'Danish', CAST(N'2021-03-31T23:02:14.397' AS DateTime), 0, NULL, NULL)
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (16, N'customer 4', N'1234', N'4', N'AB', N'XY', N'Danish', CAST(N'2021-03-31T23:03:33.280' AS DateTime), 0, NULL, NULL)
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (17, N'customer 5', N'14', N'5', N'A', N'Xyz', N'Danish', CAST(N'2021-03-31T23:07:56.650' AS DateTime), 0, NULL, NULL)
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (18, N'customer 66', N'12', N'', N'', N'', N'Danish', CAST(N'2021-03-31T23:08:15.000' AS DateTime), 0, NULL, NULL)
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (19, N'customer 6', N'123', N'', N'', N'', N'Danish', CAST(N'2021-04-02T22:23:40.623' AS DateTime), 1, N'Danish', CAST(N'2021-04-03T18:27:12.947' AS DateTime))
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (20, N'customer 4', N'1233', N'5', N'A', N'Xyz', N'Danish', CAST(N'2021-04-02T22:25:03.253' AS DateTime), 1, N'Danish', CAST(N'2021-04-02T23:38:43.543' AS DateTime))
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (21, N'customer 5', N'11', N'', N'', N'', N'Danish', CAST(N'2021-04-02T22:26:46.363' AS DateTime), 1, N'Danish', CAST(N'2021-04-02T23:37:58.667' AS DateTime))
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (22, N'jarrar', N'12345611', N'1', N'ABC', N'XYZ', N'Danish', CAST(N'2021-04-02T23:02:12.873' AS DateTime), 1, N'Danish', CAST(N'2021-04-02T23:31:16.943' AS DateTime))
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (23, N'c11', N'141', N'', N'', N'', N'Danish', CAST(N'2021-04-03T20:13:28.000' AS DateTime), 1, N'Danish', CAST(N'2021-04-03T20:14:12.707' AS DateTime))
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (24, N'c1', N'12341', N'', N'', N'', N'Danish', CAST(N'2021-04-03T20:13:47.280' AS DateTime), 1, N'Danish', CAST(N'2021-04-03T20:13:58.330' AS DateTime))
INSERT [dbo].[Customers] ([id], [name], [phone], [street], [area], [city], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt]) VALUES (25, N'customer 16', N'141', N'', N'', N'', N'Danish', CAST(N'2021-04-03T22:18:12.320' AS DateTime), 1, N'Danish', CAST(N'2021-04-03T22:18:20.780' AS DateTime))
SET IDENTITY_INSERT [dbo].[Customers] OFF
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (2, N'Cake', N'NA', N'Food', N'NA', CAST(50.00 AS Decimal(18, 2)), CAST(30.00 AS Decimal(18, 2)), 100, N'1', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 0, NULL, NULL)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (3, N'Nescafe Classic', N'NA', N'Drink', N'gram', CAST(250.00 AS Decimal(18, 2)), CAST(200.00 AS Decimal(18, 2)), 200, N'1', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 0, NULL, NULL)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (4, N'Water', N'NA', N'Drink', N'liter', CAST(60.00 AS Decimal(18, 2)), CAST(50.00 AS Decimal(18, 2)), 500, N'2', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 0, NULL, NULL)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (6, N'Coke', N'NA', N'Drink', N'litter ', CAST(90.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), 450, N'1', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 0, 0, NULL, NULL)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (7, N'Prince Biscuit', N'NA', N'Food', N'NA', CAST(18.00 AS Decimal(18, 2)), CAST(15.00 AS Decimal(18, 2)), 300, N'3', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 0, NULL, NULL)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (8, N'Bread', N'NA', N'Food', N'size', CAST(75.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), 100, N'4', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 0, NULL, NULL)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (9, N'Eggs', N'NA', N'Food', N'dozen', CAST(95.00 AS Decimal(18, 2)), CAST(90.00 AS Decimal(18, 2)), 700, N'2', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 0, 0, NULL, NULL)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (10, N'Cake', N'NA', N'Food', N'NA', CAST(50.00 AS Decimal(18, 2)), CAST(30.00 AS Decimal(18, 2)), 0, N'Danish', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 1, N'Danish', CAST(N'2021-04-03T22:30:09.223' AS DateTime))
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (11, N'Eggs', N'NA', N'Food', N'dozen', CAST(95.00 AS Decimal(18, 2)), CAST(90.00 AS Decimal(18, 2)), 0, N'Danish', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 0, 1, N'Danish', CAST(N'2021-04-03T22:29:47.417' AS DateTime))
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (12, N'Eggs A', N'NA', N'Food', N'dozen', CAST(95.00 AS Decimal(18, 2)), CAST(90.00 AS Decimal(18, 2)), 0, N'Danish', CAST(N'2021-04-03T22:30:21.353' AS DateTime), 0, 1, N'Danish', CAST(N'2021-04-03T22:32:30.757' AS DateTime))
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (13, N'Eggs B', N'NA', N'Food', N'dozen', CAST(95.00 AS Decimal(18, 2)), CAST(90.00 AS Decimal(18, 2)), 0, N'Danish', CAST(N'2021-04-03T22:32:22.887' AS DateTime), 0, 1, N'Danish', CAST(N'2021-04-03T22:32:26.587' AS DateTime))
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (14, N'EggsABC', N'NA', N'Food', N'dozen', CAST(97.00 AS Decimal(18, 2)), CAST(90.00 AS Decimal(18, 2)), 0, N'Danish', CAST(N'2021-04-03T22:59:55.253' AS DateTime), 0, 1, N'Danish', CAST(N'2021-04-03T23:01:09.277' AS DateTime))
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (15, N'EggsB', N'NA', N'Food', N'dozen', CAST(95.00 AS Decimal(18, 2)), CAST(90.00 AS Decimal(18, 2)), 0, N'Danish', CAST(N'2021-04-03T23:00:03.660' AS DateTime), 0, 1, N'Danish', CAST(N'2021-04-03T23:00:10.167' AS DateTime))
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt]) VALUES (16, N'ABC', N'NA', N'Food', N'dozen', CAST(95.00 AS Decimal(18, 2)), CAST(90.00 AS Decimal(18, 2)), 25, N'Danish', CAST(N'2021-04-03T23:04:40.147' AS DateTime), 0, 1, N'Danish', CAST(N'2021-04-03T23:07:43.620' AS DateTime))
SET IDENTITY_INSERT [dbo].[Products] OFF
SET IDENTITY_INSERT [dbo].[RunningNumber] ON 

INSERT [dbo].[RunningNumber] ([id], [type], [number], [prefix]) VALUES (1, N'SaleInvoice', 1, N'SI')
SET IDENTITY_INSERT [dbo].[RunningNumber] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([id], [name], [username], [password]) VALUES (1, N'Danish', N'danish', N'123')
SET IDENTITY_INSERT [dbo].[Users] OFF
ALTER TABLE [dbo].[Customers] ADD  CONSTRAINT [DF_Customers_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
USE [master]
GO
ALTER DATABASE [PointofSaleJAVA] SET  READ_WRITE 
GO
