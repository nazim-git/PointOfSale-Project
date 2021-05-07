USE [master]
GO
/****** Object:  Database [PointofSaleJAVA]    Script Date: 07/05/2021 9:13:45 pm ******/
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
/****** Object:  Table [dbo].[Customers]    Script Date: 07/05/2021 9:13:45 pm ******/
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
/****** Object:  Table [dbo].[Expenses]    Script Date: 07/05/2021 9:13:45 pm ******/
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
/****** Object:  Table [dbo].[InvoiceItems]    Script Date: 07/05/2021 9:13:45 pm ******/
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
/****** Object:  Table [dbo].[Invoices]    Script Date: 07/05/2021 9:13:45 pm ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoices](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[invoiceNumber] [varchar](200) NULL,
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
/****** Object:  Table [dbo].[Products]    Script Date: 07/05/2021 9:13:45 pm ******/
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
/****** Object:  Table [dbo].[Purchases]    Script Date: 07/05/2021 9:13:45 pm ******/
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
/****** Object:  Table [dbo].[RunningNumber]    Script Date: 07/05/2021 9:13:45 pm ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 07/05/2021 9:13:45 pm ******/
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
INSERT [dbo].[Customers] ([phone], [name], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'123', N'abc', N'admin', CAST(N'2021-05-07T04:44:58.637' AS DateTime), 0, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[Expenses] ON 

INSERT [dbo].[Expenses] ([id], [amount], [description], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (1, CAST(50.00 AS Decimal(18, 2)), N'awd as asd a ad as', N'Danish', CAST(N'2021-05-03T07:02:37.667' AS DateTime), 1, N'Danish', CAST(N'2021-05-03T07:20:14.103' AS DateTime), NULL)
INSERT [dbo].[Expenses] ([id], [amount], [description], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (2, CAST(20.00 AS Decimal(18, 2)), N'gf gf hgfh fgh f', N'Danish', CAST(N'2021-05-03T07:07:15.200' AS DateTime), 1, N'admin', CAST(N'2021-05-07T04:44:11.790' AS DateTime), NULL)
INSERT [dbo].[Expenses] ([id], [amount], [description], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (3, CAST(20.00 AS Decimal(18, 2)), N'gf gf hgfh fgh f', N'Danish', CAST(N'2021-05-03T07:20:46.620' AS DateTime), 1, N'Danish', CAST(N'2021-05-03T07:21:05.870' AS DateTime), NULL)
INSERT [dbo].[Expenses] ([id], [amount], [description], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (4, CAST(20.00 AS Decimal(18, 2)), N'gf gf hgfh fgh f', N'Danish', CAST(N'2021-05-03T07:20:52.667' AS DateTime), 1, N'Danish', CAST(N'2021-05-03T07:21:01.970' AS DateTime), NULL)
INSERT [dbo].[Expenses] ([id], [amount], [description], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (5, CAST(20.00 AS Decimal(18, 2)), N'gf gf hgfh fgh f', N'admin', CAST(N'2021-05-07T04:43:46.047' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Expenses] ([id], [amount], [description], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (6, CAST(20.00 AS Decimal(18, 2)), N'gf gf hgfh fgh f', N'admin', CAST(N'2021-05-07T04:43:49.440' AS DateTime), 1, N'admin', CAST(N'2021-05-07T04:44:05.217' AS DateTime), 0)
INSERT [dbo].[Expenses] ([id], [amount], [description], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (7, CAST(20.00 AS Decimal(18, 2)), N'gf gf hgfh fgh f', N'admin', CAST(N'2021-05-07T04:43:52.230' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Expenses] ([id], [amount], [description], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (8, CAST(20.00 AS Decimal(18, 2)), N'gf gf hgfh fgh f', N'admin', CAST(N'2021-05-07T04:43:55.057' AS DateTime), 0, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[Expenses] OFF
SET IDENTITY_INSERT [dbo].[InvoiceItems] ON 

INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (13, 20, N'Bread', 5, N'size', CAST(75.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), CAST(375.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T04:57:48.967' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (14, 20, N'Coke', 5, N'litter ', CAST(90.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), CAST(450.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T04:57:48.977' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (15, 21, N'Bread', 2, N'size', CAST(75.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), CAST(150.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T05:03:43.373' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (16, 21, N'Cake', 4, N'NA', CAST(50.00 AS Decimal(18, 2)), CAST(30.00 AS Decimal(18, 2)), CAST(200.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T05:03:43.377' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[InvoiceItems] ([id], [invoiceId], [title], [quantity], [unit], [salePrice], [purchasePrice], [subTotal], [createdBy], [createdAt], [isDeleted], [deletedby], [deletedAt], [isSynced]) VALUES (17, 22, N'Bread', 3, N'size', CAST(75.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), CAST(225.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T05:08:48.693' AS DateTime), 0, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[InvoiceItems] OFF
SET IDENTITY_INSERT [dbo].[Invoices] ON 

INSERT [dbo].[Invoices] ([id], [invoiceNumber], [customerPhone], [customer], [total], [discountPercent], [discountAmount], [totalToPay], [received], [change], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (18, N'SI-30', N'123', N'abc', CAST(825.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(825.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(-825.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T04:54:05.840' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Invoices] ([id], [invoiceNumber], [customerPhone], [customer], [total], [discountPercent], [discountAmount], [totalToPay], [received], [change], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (19, N'SI-31', N'123', N'abc', CAST(375.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(375.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(-375.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T04:55:03.977' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Invoices] ([id], [invoiceNumber], [customerPhone], [customer], [total], [discountPercent], [discountAmount], [totalToPay], [received], [change], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (20, N'SI-32', N'123', N'abc', CAST(825.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(825.00 AS Decimal(18, 2)), CAST(1000.00 AS Decimal(18, 2)), CAST(175.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T04:57:48.957' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Invoices] ([id], [invoiceNumber], [customerPhone], [customer], [total], [discountPercent], [discountAmount], [totalToPay], [received], [change], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (21, N'SI-33', N'123', N'abc', CAST(350.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(350.00 AS Decimal(18, 2)), CAST(500.00 AS Decimal(18, 2)), CAST(150.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T05:03:43.363' AS DateTime), 0, NULL, NULL, 0)
INSERT [dbo].[Invoices] ([id], [invoiceNumber], [customerPhone], [customer], [total], [discountPercent], [discountAmount], [totalToPay], [received], [change], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (22, N'SI-34', N'123', N'abc', CAST(225.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), CAST(225.00 AS Decimal(18, 2)), CAST(300.00 AS Decimal(18, 2)), CAST(75.00 AS Decimal(18, 2)), N'admin', CAST(N'2021-05-07T05:08:48.680' AS DateTime), 0, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[Invoices] OFF
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'a', N'', N'AAA', CAST(10.00 AS Decimal(18, 2)), CAST(5.00 AS Decimal(18, 2)), 0, N'admin', CAST(N'2021-05-06T07:01:46.053' AS DateTime), 1, 1, N'admin', CAST(N'2021-05-07T04:31:13.057' AS DateTime), NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'abcbbcbc', N'', N'a', CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), 0, N'admin', CAST(N'2021-05-06T07:03:24.390' AS DateTime), 1, 1, N'admin', CAST(N'2021-05-06T07:43:24.613' AS DateTime), NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'aewre', N'', N'AAA', CAST(123.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), 0, N'admin', CAST(N'2021-05-06T07:42:46.840' AS DateTime), 1, 1, N'admin', CAST(N'2021-05-07T04:36:39.237' AS DateTime), 0)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'as', N'', N'a', CAST(1.25 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), 0, N'admin', CAST(N'2021-05-06T07:09:37.120' AS DateTime), 1, 1, N'admin', CAST(N'2021-05-07T04:41:50.840' AS DateTime), NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'asd', N'', N'ads', CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), 0, N'admin', CAST(N'2021-05-06T07:04:11.817' AS DateTime), 1, 1, N'admin', CAST(N'2021-05-07T04:43:26.497' AS DateTime), NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Bread', N'NA', N'size', CAST(75.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), 70, N'4', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 0, NULL, NULL, NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Cake', N'NA', N'NA', CAST(50.00 AS Decimal(18, 2)), CAST(30.00 AS Decimal(18, 2)), 96, N'1', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 0, NULL, NULL, NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Coke', N'NA', N'litter ', CAST(90.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), 440, N'1', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 0, 0, NULL, NULL, NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Eggs', N'NA', N'dozen', CAST(120.00 AS Decimal(18, 2)), CAST(100.00 AS Decimal(18, 2)), 795, N'2', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 0, 0, NULL, NULL, NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Nescafe Classic', N'NA', N'gram', CAST(250.00 AS Decimal(18, 2)), CAST(200.00 AS Decimal(18, 2)), 200, N'1', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 0, NULL, NULL, NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Prince Biscuit', N'NA', N'NA', CAST(18.00 AS Decimal(18, 2)), CAST(15.00 AS Decimal(18, 2)), 300, N'3', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 1, N'admin', CAST(N'2021-05-07T04:31:27.053' AS DateTime), NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'sa', N'', N'asas', CAST(0.00 AS Decimal(18, 2)), CAST(0.00 AS Decimal(18, 2)), 0, N'admin', CAST(N'2021-05-06T07:04:00.393' AS DateTime), 1, 1, N'admin', CAST(N'2021-05-07T04:36:46.720' AS DateTime), NULL)
INSERT [dbo].[Products] ([title], [description], [unit], [salePrice], [purchasePrice], [stock], [createdBy], [createdAt], [status], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (N'Water', N'NA', N'liter', CAST(60.00 AS Decimal(18, 2)), CAST(50.00 AS Decimal(18, 2)), 500, N'2', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 1, 1, N'admin', CAST(N'2021-05-07T04:31:19.173' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[Purchases] ON 

INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (1, N'a', CAST(1.00 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)), 5, N'a', N'aa', N'aa', CAST(N'2021-04-03T17:15:04.840' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (2, N'Cake', CAST(1.00 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)), 22, N'aavv', N'aavv', N'Danish', CAST(N'2021-05-02T23:45:23.347' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (3, N'Cake', CAST(22.00 AS Decimal(18, 2)), CAST(44.00 AS Decimal(18, 2)), 4, N'df', N'dd', N'Danish', CAST(N'2021-05-03T04:36:00.297' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (4, N'a', CAST(12.00 AS Decimal(18, 2)), CAST(15.00 AS Decimal(18, 2)), 10, N'QWE', N'AA', N'Danish', CAST(N'2021-05-03T05:25:48.807' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (5, N'a', CAST(11.00 AS Decimal(18, 2)), CAST(111.00 AS Decimal(18, 2)), 15, N'qweqw', N'AAA', N'Danish', CAST(N'2021-05-03T05:26:53.180' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (6, N'a', CAST(12.00 AS Decimal(18, 2)), CAST(123.00 AS Decimal(18, 2)), 14, N'qew', N'aaa', N'Danish', CAST(N'2021-05-03T05:28:16.283' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (7, N'Cake', CAST(12.00 AS Decimal(18, 2)), CAST(22.00 AS Decimal(18, 2)), 13, N'qew', N'a', N'Danish', CAST(N'2021-05-03T05:30:09.237' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (8, N'a', CAST(1222.00 AS Decimal(18, 2)), CAST(123123.00 AS Decimal(18, 2)), 124, N'qew', N'qwe', N'Danish', CAST(N'2021-05-03T05:34:50.440' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (9, N'a', CAST(1222.00 AS Decimal(18, 2)), CAST(123123.00 AS Decimal(18, 2)), 124, N'qew', N'qwe', N'Danish', CAST(N'2021-05-03T05:36:10.310' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (10, N'a', CAST(1222.00 AS Decimal(18, 2)), CAST(123123.00 AS Decimal(18, 2)), 124, N'qew', N'qwe', N'Danish', CAST(N'2021-05-03T05:36:18.710' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (11, N'a', CAST(1.00 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)), 10, N'aa', N'a', N'Danish', CAST(N'2021-05-03T05:37:28.397' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (12, N'a', CAST(112.00 AS Decimal(18, 2)), CAST(223.00 AS Decimal(18, 2)), 13, N'qwe', N'AA', N'Danish', CAST(N'2021-05-03T05:38:22.173' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (13, N'a', CAST(2.00 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)), 14, N'q', N'q', N'Danish', CAST(N'2021-05-03T05:39:09.533' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (14, N'Eggs', CAST(10.00 AS Decimal(18, 2)), CAST(15.00 AS Decimal(18, 2)), 20, N'Pieces', N'AA', N'Danish', CAST(N'2021-05-03T05:45:53.193' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (15, N'Eggs', CAST(100.00 AS Decimal(18, 2)), CAST(120.00 AS Decimal(18, 2)), 50, N'dozen', N'RR', N'Danish', CAST(N'2021-05-03T05:53:43.763' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (16, N'Eggs', CAST(100.00 AS Decimal(18, 2)), CAST(120.00 AS Decimal(18, 2)), 80, N'dozen', N'q', N'Danish', CAST(N'2021-05-03T05:54:56.250' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Purchases] ([id], [title], [purchasePrice], [salePrice], [quantity], [unit], [supplier], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (17, N'a', CAST(5.00 AS Decimal(18, 2)), CAST(10.00 AS Decimal(18, 2)), 1, N'AAA', N'AAA', N'admin', CAST(N'2021-05-06T08:33:01.577' AS DateTime), 0, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Purchases] OFF
SET IDENTITY_INSERT [dbo].[RunningNumber] ON 

INSERT [dbo].[RunningNumber] ([id], [type], [number], [prefix]) VALUES (1, N'SaleInvoice', 35, N'SI')
SET IDENTITY_INSERT [dbo].[RunningNumber] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([id], [name], [username], [password], [isAdmin], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (1, N'Danish', N'danish', N'111', 0, NULL, NULL, 0, NULL, NULL, NULL)
INSERT [dbo].[Users] ([id], [name], [username], [password], [isAdmin], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (2, N'admin', N'admin', N'admin', 1, NULL, NULL, 0, NULL, NULL, NULL)
INSERT [dbo].[Users] ([id], [name], [username], [password], [isAdmin], [createdBy], [createdAt], [isDeleted], [deletedBy], [deletedAt], [isSynced]) VALUES (3, N'test', N'test', N'123', 0, NULL, NULL, 1, N'admin', CAST(N'2021-05-04T22:42:38.827' AS DateTime), NULL)
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
