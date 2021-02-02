USE [master]
GO
/****** Object:  Database [PointofSaleJAVA]    Script Date: 03/02/2021 12:44:49 am ******/
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
/****** Object:  Table [dbo].[Products]    Script Date: 03/02/2021 12:44:49 am ******/
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
	[createdBy] [int] NULL,
	[createdDate] [datetime] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RunningNumber]    Script Date: 03/02/2021 12:44:50 am ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 03/02/2021 12:44:50 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](max) NOT NULL,
	[username] [varchar](max) NOT NULL,
	[password] [varchar](max) NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [createdBy], [createdDate], [status]) VALUES (2, N'Cake', N'NA', N'Food', N'NA', CAST(50.00 AS Decimal(18, 2)), CAST(30.00 AS Decimal(18, 2)), 1, CAST(N'2021-01-31T15:32:48.000' AS DateTime), 1)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [createdBy], [createdDate], [status]) VALUES (3, N'Nescafe Classic', N'NA', N'Drink', N'gram', CAST(250.00 AS Decimal(18, 2)), CAST(200.00 AS Decimal(18, 2)), 1, CAST(N'2021-01-30T15:32:48.000' AS DateTime), 1)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [createdBy], [createdDate], [status]) VALUES (4, N'Water', N'NA', N'Drink', N'liter', CAST(60.00 AS Decimal(18, 2)), CAST(50.00 AS Decimal(18, 2)), 2, CAST(N'2021-01-29T15:32:48.000' AS DateTime), 1)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [createdBy], [createdDate], [status]) VALUES (6, N'Coke', N'NA', N'Drink', N'litter ', CAST(90.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), 1, CAST(N'2021-01-28T15:32:48.000' AS DateTime), 1)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [createdBy], [createdDate], [status]) VALUES (7, N'Prince Biscuit', N'NA', N'Food', N'NA', CAST(18.00 AS Decimal(18, 2)), CAST(15.00 AS Decimal(18, 2)), 3, CAST(N'2021-01-27T15:32:48.000' AS DateTime), 1)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [createdBy], [createdDate], [status]) VALUES (8, N'Bread', N'NA', N'Food', N'size', CAST(75.00 AS Decimal(18, 2)), CAST(70.00 AS Decimal(18, 2)), 4, CAST(N'2021-01-22T15:32:48.000' AS DateTime), 1)
INSERT [dbo].[Products] ([id], [title], [description], [category], [unit], [salePrice], [purchasePrice], [createdBy], [createdDate], [status]) VALUES (9, N'Eggs', N'NA', N'Food', N'dozen', CAST(95.00 AS Decimal(18, 2)), CAST(90.00 AS Decimal(18, 2)), 2, CAST(N'2021-01-10T15:32:48.000' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Products] OFF
SET IDENTITY_INSERT [dbo].[RunningNumber] ON 

INSERT [dbo].[RunningNumber] ([id], [type], [number], [prefix]) VALUES (1, N'SaleInvoice', 1, N'SI')
SET IDENTITY_INSERT [dbo].[RunningNumber] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([ID], [name], [username], [password]) VALUES (1, N'Danish', N'danish', N'123')
SET IDENTITY_INSERT [dbo].[Users] OFF
USE [master]
GO
ALTER DATABASE [PointofSaleJAVA] SET  READ_WRITE 
GO
