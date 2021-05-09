USE [master]
GO
/****** Object:  Database [PointofSaleJAVA]    Script Date: 09/05/2021 1:54:55 pm ******/
CREATE DATABASE [PointofSaleJAVA]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PointofSaleJAVA', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\PointofSaleJAVA.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PointofSaleJAVA_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\PointofSaleJAVA_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
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
ALTER DATABASE [PointofSaleJAVA] SET  ENABLE_BROKER 
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
/****** Object:  Table [dbo].[Customers]    Script Date: 09/05/2021 1:54:56 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[phone] [varchar](70) NOT NULL,
	[name] [varchar](100) NULL,
	[createdBy] [varchar](100) NULL,
	[createdAt] [datetime] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [varchar](100) NULL,
	[deletedAt] [datetime] NULL,
	[isSynced] [bit] NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Expenses]    Script Date: 09/05/2021 1:54:56 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Expenses](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[amount] [decimal](18, 2) NULL,
	[description] [varchar](250) NULL,
	[createdBy] [varchar](100) NULL,
	[createdAt] [datetime] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [varchar](100) NULL,
	[deletedAt] [datetime] NULL,
	[isSynced] [bit] NULL,
 CONSTRAINT [PK_Expenses] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InvoiceItems]    Script Date: 09/05/2021 1:54:56 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceItems](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[invoiceId] [int] NULL,
	[title] [varchar](200) NULL,
	[quantity] [int] NULL,
	[unit] [varchar](200) NULL,
	[salePrice] [decimal](18, 2) NULL,
	[purchasePrice] [decimal](18, 2) NULL,
	[subTotal] [decimal](18, 2) NULL,
	[createdBy] [varchar](200) NULL,
	[createdAt] [datetime] NULL,
	[isDeleted] [bit] NULL,
	[deletedby] [varchar](200) NULL,
	[deletedAt] [datetime] NULL,
	[isSynced] [bit] NULL,
 CONSTRAINT [PK_InvoiceItems] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoices]    Script Date: 09/05/2021 1:54:56 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoices](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[invoiceNumber] [varchar](200) NULL,
	[refInvoiceNumber] [varchar](200) NULL,
	[customerPhone] [varchar](70) NULL,
	[customer] [varchar](200) NULL,
	[total] [decimal](18, 2) NULL,
	[discountPercent] [decimal](18, 2) NULL,
	[discountAmount] [decimal](18, 2) NULL,
	[totalToPay] [decimal](18, 2) NULL,
	[received] [decimal](18, 2) NULL,
	[change] [decimal](18, 2) NULL,
	[createdBy] [varchar](200) NULL,
	[createdAt] [datetime] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [varchar](200) NULL,
	[deletedAt] [datetime] NULL,
	[isSynced] [bit] NULL,
 CONSTRAINT [PK_Invoices] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 09/05/2021 1:54:56 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[title] [varchar](200) NOT NULL,
	[description] [varchar](100) NULL,
	[unit] [varchar](50) NULL,
	[salePrice] [decimal](18, 2) NULL,
	[purchasePrice] [decimal](18, 2) NULL,
	[stock] [int] NULL,
	[createdBy] [varchar](100) NULL,
	[createdAt] [datetime] NULL,
	[status] [bit] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [varchar](100) NULL,
	[deletedAt] [datetime] NULL,
	[isSynced] [bit] NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Purchases]    Script Date: 09/05/2021 1:54:56 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Purchases](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](200) NULL,
	[purchasePrice] [decimal](18, 2) NULL,
	[salePrice] [decimal](18, 2) NULL,
	[quantity] [int] NULL,
	[unit] [varchar](100) NULL,
	[supplier] [varchar](100) NULL,
	[createdBy] [varchar](100) NULL,
	[createdAt] [datetime] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [varchar](100) NULL,
	[deletedAt] [datetime] NULL,
	[isSynced] [bit] NULL,
 CONSTRAINT [PK_Purchases] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RunningNumber]    Script Date: 09/05/2021 1:54:56 pm ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 09/05/2021 1:54:56 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](max) NOT NULL,
	[username] [varchar](max) NOT NULL,
	[password] [varchar](max) NOT NULL,
	[isAdmin] [bit] NULL,
	[createdBy] [varchar](200) NULL,
	[createdAt] [datetime] NULL,
	[isDeleted] [bit] NULL,
	[deletedBy] [varchar](200) NULL,
	[deletedAt] [datetime] NULL,
	[isSynced] [bit] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Customers] ([phone], [name], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'123456', N'Customer A', N'admin', CAST(N'2021-05-08T15:09:21.707' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Customers] ([phone], [name], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'1234567', N'Customer C', N'admin', CAST(N'2021-05-08T15:09:50.400' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Customers] ([phone], [name], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'123457', N'Customer B', N'admin', CAST(N'2021-05-08T15:09:37.227' AS DateTime), 0, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[InvoiceItems] ON 

INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (14, 43, N'Product A', 9, N'Pcs', CAST(50.00 AS Decimal(18, 2)), CAST(20.00 AS Decimal(18, 2)), CAST(450.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T11:57:47.920' AS DateTime), 1, N'admin', CAST(N'2021-05-09T12:00:20.340' AS DateTime), 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (15, 43, N'Product B', 10, N'Kg', CAST(14.00 AS Decimal(18, 2)), CAST(10.00 AS Decimal(18, 2)), CAST(140.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T11:57:52.200' AS DateTime), 1, N'admin', CAST(N'2021-05-09T12:00:20.340' AS DateTime), 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (16, 43, N'Product C', 5, N'Grams', CAST(36.00 AS Decimal(18, 2)), CAST(30.00 AS Decimal(18, 2)), CAST(180.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T11:57:54.243' AS DateTime), 1, N'admin', CAST(N'2021-05-09T12:00:20.340' AS DateTime), 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (17, 43, N'Product D', 10, N'Kg', CAST(12.00 AS Decimal(18, 2)), CAST(10.00 AS Decimal(18, 2)), CAST(120.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T11:57:58.250' AS DateTime), 1, N'admin', CAST(N'2021-05-09T12:00:20.340' AS DateTime), 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (18, 43, N'Product E', 5, N'Pcs', CAST(22.00 AS Decimal(18, 2)), CAST(20.00 AS Decimal(18, 2)), CAST(110.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T11:57:59.787' AS DateTime), 1, N'admin', CAST(N'2021-05-09T12:00:20.340' AS DateTime), 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (19, 44, N'Product B', 10, N'Kg', CAST(14.00 AS Decimal(18, 2)), CAST(10.00 AS Decimal(18, 2)), CAST(140.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T12:12:47.153' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (20, 44, N'Product C', 5, N'Grams', CAST(36.00 AS Decimal(18, 2)), CAST(30.00 AS Decimal(18, 2)), CAST(180.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T12:12:47.160' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (21, 44, N'Product D', 10, N'Kg', CAST(12.00 AS Decimal(18, 2)), CAST(10.00 AS Decimal(18, 2)), CAST(120.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T12:12:47.163' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (22, 44, N'Product E', 5, N'Pcs', CAST(22.00 AS Decimal(18, 2)), CAST(20.00 AS Decimal(18, 2)), CAST(110.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T12:12:47.163' AS DateTime), 0, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[InvoiceItems] OFF
SET IDENTITY_INSERT [dbo].[Invoices] ON 

INSERT [dbo].[Invoices] ([id], [invoiceNumber], [refInvoiceNumber], [customerPhone], [customer], [total], [discountPercent], [discountAmount], [totalToPay], [received], [change], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (43, N'SI-56', NULL, N'123456', N'Customer A', CAST(1000.00 AS Decimal(18, 2)), CAST(5.00 AS Decimal(18, 2)), CAST(50.00 AS Decimal(18, 2)), CAST(950.00 AS Decimal(18, 2)), CAST(1000.00 AS Decimal(18, 2)), CAST(50.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T11:56:52.857' AS DateTime), 1, N'admin', CAST(N'2021-05-09T12:00:07.577' AS DateTime), 0)
INSERT [dbo].[Invoices] ([id], [invoiceNumber], [refInvoiceNumber], [customerPhone], [customer], [total], [discountPercent], [discountAmount], [totalToPay], [received], [change], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (44, N'SI-57', N'SI-56', N'123456', N'Customer A', CAST(550.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(550.00 AS Decimal(18, 2)), CAST(600.00 AS Decimal(18, 2)), CAST(50.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-09T12:07:11.563' AS DateTime), 0, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[Invoices] OFF
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Product A', N'', N'Pcs', CAST(50.00 AS Decimal(18, 2)), CAST(20.00 AS Decimal(18, 2)), 949, N'admin', CAST(N'2021-05-08T15:03:41.807' AS DateTime), 1, 0, NULL, NULL, 0)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Product B', N'', N'Kg', CAST(14.00 AS Decimal(18, 2)), CAST(10.00 AS Decimal(18, 2)), 390, N'admin', CAST(N'2021-05-08T15:03:54.093' AS DateTime), 1, 0, NULL, NULL, 0)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Product C', N'', N'Grams', CAST(36.00 AS Decimal(18, 2)), CAST(30.00 AS Decimal(18, 2)), 250, N'admin', CAST(N'2021-05-08T15:04:11.897' AS DateTime), 1, 0, NULL, NULL, 0)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Product D', N'', N'Kg', CAST(12.00 AS Decimal(18, 2)), CAST(10.00 AS Decimal(18, 2)), 150, N'admin', CAST(N'2021-05-08T15:15:41.630' AS DateTime), 1, 0, NULL, NULL, 0)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Product E', N'', N'Pcs', CAST(22.00 AS Decimal(18, 2)), CAST(20.00 AS Decimal(18, 2)), 660, N'admin', CAST(N'2021-05-08T15:17:43.270' AS DateTime), 1, 0, NULL, NULL, 0)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Product F', N'', N'Grams', CAST(70.00 AS Decimal(18, 2)), CAST(50.00 AS Decimal(18, 2)), 35, N'admin', CAST(N'2021-05-08T15:19:48.167' AS DateTime), 0, 0, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[Purchases] ON 

INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (18, N'Product A', CAST(20.00 AS Decimal(18, 2)), CAST(50.00 AS Decimal(18, 2)), 1000, N'Pcs', N'Supplier ABC', N'admin', CAST(N'2021-05-08T15:07:56.813' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (19, N'Product B', CAST(10.00 AS Decimal(18, 2)), CAST(14.00 AS Decimal(18, 2)), 500, N'Kg', N'Supplier XYZ', N'admin', CAST(N'2021-05-08T15:08:14.737' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (20, N'Product C', CAST(30.00 AS Decimal(18, 2)), CAST(36.00 AS Decimal(18, 2)), 300, N'Grams', N'Supplier EFG', N'admin', CAST(N'2021-05-08T15:08:36.803' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (21, N'Product D', CAST(10.00 AS Decimal(18, 2)), CAST(12.00 AS Decimal(18, 2)), 200, N'Kg', N'Supplier ABC', N'admin', CAST(N'2021-05-08T15:21:36.747' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (22, N'Product E', CAST(20.00 AS Decimal(18, 2)), CAST(22.00 AS Decimal(18, 2)), 700, N'Pcs', N'Supplier EFG', N'admin', CAST(N'2021-05-08T15:21:54.263' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (23, N'Product F', CAST(50.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), 50, N'Grams', N'Supplier XYZ', N'admin', CAST(N'2021-05-08T15:22:14.143' AS DateTime), 0, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[Purchases] OFF
SET IDENTITY_INSERT [dbo].[RunningNumber] ON 

INSERT [dbo].[RunningNumber] ([id], [type], [number], [prefix]) VALUES (1, N'SaleInvoice', 58, N'SI')
SET IDENTITY_INSERT [dbo].[RunningNumber] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([id], [name], [username], [password], [isAdmin], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (2, N'admin', N'admin', N'admin', 1, NULL, NULL, 0, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Users] OFF
ALTER TABLE [dbo].[Customers] ADD  CONSTRAINT [DF_Customers_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[Customers] ADD  CONSTRAINT [DF_Customers_isSynced]  DEFAULT ((0)) FOR [isSynced]
GO
ALTER TABLE [dbo].[Expenses] ADD  CONSTRAINT [DF_Expenses_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[Expenses] ADD  CONSTRAINT [DF_Expenses_isSynced]  DEFAULT ((0)) FOR [isSynced]
GO
ALTER TABLE [dbo].[InvoiceItems] ADD  CONSTRAINT [DF_InvoiceItems_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[InvoiceItems] ADD  CONSTRAINT [DF_InvoiceItems_isSynced]  DEFAULT ((0)) FOR [isSynced]
GO
ALTER TABLE [dbo].[Invoices] ADD  CONSTRAINT [DF_Invoices_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[Invoices] ADD  CONSTRAINT [DF_Invoices_isSynced]  DEFAULT ((0)) FOR [isSynced]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_salePrice]  DEFAULT ((0)) FOR [salePrice]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_purchasePrice]  DEFAULT ((0)) FOR [purchasePrice]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_stock]  DEFAULT ((0)) FOR [stock]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[Products] ADD  CONSTRAINT [DF_Products_isSynced]  DEFAULT ((0)) FOR [isSynced]
GO
ALTER TABLE [dbo].[Purchases] ADD  CONSTRAINT [DF_Purchases_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[Purchases] ADD  CONSTRAINT [DF_Purchases_isSynced]  DEFAULT ((0)) FOR [isSynced]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_Users_isDeleted]  DEFAULT ((0)) FOR [isDeleted]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_Users_isSynced]  DEFAULT ((0)) FOR [isSynced]
GO
ALTER TABLE [dbo].[InvoiceItems]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceItems_Invoices] FOREIGN KEY([invoiceId])
REFERENCES [dbo].[Invoices] ([id])
GO
ALTER TABLE [dbo].[InvoiceItems] CHECK CONSTRAINT [FK_InvoiceItems_Invoices]
GO
ALTER TABLE [dbo].[InvoiceItems]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceItems_Products] FOREIGN KEY([title])
REFERENCES [dbo].[Products] ([title])
GO
ALTER TABLE [dbo].[InvoiceItems] CHECK CONSTRAINT [FK_InvoiceItems_Products]
GO
ALTER TABLE [dbo].[Invoices]  WITH CHECK ADD  CONSTRAINT [FK_Invoices_Customers] FOREIGN KEY([customerPhone])
REFERENCES [dbo].[Customers] ([phone])
GO
ALTER TABLE [dbo].[Invoices] CHECK CONSTRAINT [FK_Invoices_Customers]
GO
ALTER TABLE [dbo].[Purchases]  WITH CHECK ADD  CONSTRAINT [FK_Purchases_Products] FOREIGN KEY([title])
REFERENCES [dbo].[Products] ([title])
GO
ALTER TABLE [dbo].[Purchases] CHECK CONSTRAINT [FK_Purchases_Products]
GO
USE [master]
GO
ALTER DATABASE [PointofSaleJAVA] SET  READ_WRITE 
GO
