
USE RenRenDB
GO
IF EXISTS (SELECT * FROM SYSINDEXES WHERE NAME='IX_TaskCityRelation_TaskId')
DROP INDEX TaskCityRelation.IX_TaskCityRelation_TaskId

--创建索引
CREATE NONCLUSTERED INDEX IX_TaskCityRelation_TaskId
ON TaskCityRelation(TaskId)
GO

IF EXISTS (SELECT * FROM SYSINDEXES WHERE NAME='IX_TaskCityRelation_CityCode')
DROP INDEX TaskCityRelation.IX_TaskCityRelation_CityCode

CREATE NONCLUSTERED INDEX IX_TaskCityRelation_CityCode 
ON TaskCityRelation(CityCode)  
GO


IF EXISTS (SELECT * FROM SYSINDEXES WHERE NAME='IX_RenRenTask_TagId')
DROP INDEX RenRenTask.IX_RenRenTask_TagId

--创建索引
CREATE NONCLUSTERED INDEX IX_RenRenTask_TagId 
ON RenRenTask(TagId)  
GO


IF EXISTS (SELECT * FROM SYSINDEXES WHERE NAME='IX_ClienterTask_CityCode')
DROP INDEX ClienterTask.IX_ClienterTask_CityCode

CREATE NONCLUSTERED INDEX IX_ClienterTask_CityCode 
ON ClienterTask(CityCode)  
GO
