package org.netsharp.persistence.interceptor;

import org.netsharp.core.Mtable;
import org.netsharp.dataccess.IDataAccess;
import org.netsharp.entity.IPersistable;

public class PersistInterceptorConcurrency implements IPersistInterceptor {
	
	 public void persistNew(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
     {
         //this.CheckConcurrency(entity, SqlGeneratorType.Insert, 1, dao);

         //entity.setSysEntityState( SysEntityState.Transient );
     }

     public void persist(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
     {
         //this.CheckConcurrency(entity, SqlGeneratorType.Update, arg.ExecutedCount, dao);

         //entity.setSysEntityState( SysEntityState.Transient);
     }

     public void delete(IPersistable entity, Mtable meta, IDataAccess dao, PersistInterceptorArg arg)
     {
         //this.CheckConcurrency(entity, SqlGeneratorType.Delete, arg.ExecutedCount, dao);
     }
//
//     /// <summary>
//     /// 并发控制
//     /// </summary>
//     /// <param name="entity"></param>
//     /// <param name="operationType"></param>
//     /// <param name="count">ExecuteNonQuery 返回的影响行数</param>
//     public void CheckConcurrency(ISysEntity entity, SqlGeneratorType operationType, int count, IDataAccess dao)
//     {
//         Mtable meta = MtableManager.GetMtable(entity);
//
//         if (!PersistUtility.IsConcurrency(meta))
//         {
//             SyncTs(entity, meta,dao);
//
//             return;
//         }
//
//         if (count == 0 && (operationType == SqlGeneratorType.Update || operationType == SqlGeneratorType.Delete))
//         {
//             String cmdText = "SELECT IdUpdator,Updator FROM " + meta.TableName + Oql.WithNolock + " WHERE Id=@Id";
//             QueryParameterCollection qpc = new QueryParameterCollection();
//             qpc.Add("@Id", entity.Id, DbType.Guid);
//
//             ITable table = dao.ExecuteSharpTable(cmdText, qpc);
//             String userName = null;
//
//             if (table.Count > 0)
//             {
//                 IPersistable concurrencySysEntity = table[0] as IPersistable;
//
//                 object idUpdator = concurrencySysEntity["IdUpdator"];
//                 if (idUpdator != null)
//                 {
//                     Session session = getSession();
//
//                     if ((Guid)idUpdator == session.UserId)
//                     {
//                         userName = "(您自己," + session.UserName + ")";
//                     }
//                     else
//                     {
//                         object updator = concurrencySysEntity["Updator"];
//                         if (updator != null)
//                         {
//                             userName = "(" + updator + ")";
//                         }
//                     }
//                 }
//             }
//
//             String message = "并发错误，数据(" + meta.Name + ")已被" + userName + "修改！";
//
//             throw new DbConcurrencyException(message);
//         }
//         else
//         {
//             SyncTs(entity, meta,dao);
//         }
//     }
//
//     private void SyncTs(ISysEntity entity, Mtable meta, IDataAccess dao)
//     {
//         if (meta == null)
//         {
//             meta = MtableManager.GetMtable(entity);
//         }
//
//         QueryParameterCollection qpc = new QueryParameterCollection();
//         if (entity.SysEntityState != SysEntityState.Deleted)
//         {
//             String cmdText = "SELECT Ts FROM " + meta.TableName + Oql.WithNolock + " WHERE Id=@Id";
//
//             qpc.Add("@Id", entity.Id, DbType.Guid);
//             entity["Ts"] = dao.ExecuteScalar(cmdText, qpc);
//         }
//     }
//
//
//     private Session getSession()
//     {
//         if (ServiceContext.Current == null || ServiceContext.Current.Session == null)
//         {
//             return SessionManager.Current;
//         }
//         else
//         {
//             return ServiceContext.Current.Session;
//         }
//     }
}
