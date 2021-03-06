USE [RenRenDB]
GO
/****** Object:  Table [dbo].[TaskCityRelation]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaskCityRelation](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[TaskId] [bigint] NOT NULL,
	[CityCode] [int] NOT NULL,
	[BusinessId] [bigint] NOT NULL,
	[CityName] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_TaskCityRelation] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskCityRelation', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskCityRelation', @level2type=N'COLUMN',@level2name=N'TaskId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'城市编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskCityRelation', @level2type=N'COLUMN',@level2name=N'CityCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商户Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskCityRelation', @level2type=N'COLUMN',@level2name=N'BusinessId'
GO
ALTER TABLE [dbo].[TaskCityRelation] ADD  CONSTRAINT [DF_TaskCityRelation_CityCode]  DEFAULT ((0)) FOR [CityCode]
GO
