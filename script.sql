USE [master]
GO
/****** Object:  Database [PointofSaleJAVA]    Script Date: 13/05/2021 7:34:48 am ******/
CREATE DATABASE [PointofSaleJAVA]
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
/****** Object:  Table [dbo].[Customers]    Script Date: 13/05/2021 7:34:48 am ******/
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
	[oldPhone] [varchar](70) NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Expenses]    Script Date: 13/05/2021 7:34:48 am ******/
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
/****** Object:  Table [dbo].[InvoiceItems]    Script Date: 13/05/2021 7:34:48 am ******/
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
/****** Object:  Table [dbo].[Invoices]    Script Date: 13/05/2021 7:34:48 am ******/
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
/****** Object:  Table [dbo].[Products]    Script Date: 13/05/2021 7:34:48 am ******/
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
/****** Object:  Table [dbo].[Purchases]    Script Date: 13/05/2021 7:34:48 am ******/
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
/****** Object:  Table [dbo].[RunningNumber]    Script Date: 13/05/2021 7:34:48 am ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 13/05/2021 7:34:48 am ******/
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
SET IDENTITY_INSERT [dbo].[RunningNumber] ON 

INSERT [dbo].[RunningNumber] ([id], [type], [number], [prefix]) VALUES (1, N'SaleInvoice', 1, N'SI')
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
