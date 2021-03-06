USE [RenRenDB]
GO
/****** Object:  Table [dbo].[TemplateControl]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TemplateControl](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ControlType] [varchar](200) NOT NULL,
	[ContolName] [nvarchar](50) NOT NULL,
	[Status] [smallint] NOT NULL,
	[Remark] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_ControlType] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'0未启用 默认1启用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TemplateControl', @level2type=N'COLUMN',@level2name=N'Status'
GO
ALTER TABLE [dbo].[TemplateControl] ADD  CONSTRAINT [DF_TemplateControl_ContolName]  DEFAULT ('') FOR [ContolName]
GO
ALTER TABLE [dbo].[TemplateControl] ADD  CONSTRAINT [DF_TemplateControl_Status]  DEFAULT ((1)) FOR [Status]
GO
ALTER TABLE [dbo].[TemplateControl] ADD  CONSTRAINT [DF_TemplateControl_Remark]  DEFAULT ('') FOR [Remark]
GO
