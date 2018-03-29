package com.suit.shiro;

import org.apache.shiro.session.mgt.eis.MemorySessionDAO;

public class MySessionDAO extends MemorySessionDAO{

//	protected SysUserSessionService sysUserSessionService;
//
//	public void setSysUserSessionService(SysUserSessionService sysUserSessionService) {
//		this.sysUserSessionService = sysUserSessionService;
//	}
//
//	@Override
//	public void update(Session session) throws UnknownSessionException {
////		String sessionid = session.getId().toString();
////		try {
////			SysUserSession sysUserSession = sysUserSessionService.getById(sessionid);
////			
////			if(sysUserSession == null){
////				throw new UnknownSessionException("There is no session with id [" + sessionid + "]");
////			}
////			
////			sysUserSessionService.save(sysUserSession);
////		} catch (CoreException e) {
////			e.printStackTrace();
////			throw new RuntimeException(e.getCode() + "--" +e.getMessage());
////		}
//		
//		super.update(session);
//	}
//
//	@Override
//	public void delete(Session session) {
//		super.delete(session);
////		sysUserSessionService.delBySessionid(session.getId().toString());//从数据库中删除
////		String sessionid = session.getId().toString();
////		try {
////			SysUserSession sysUserSession = sysUserSessionService.getById(sessionid);
////			
////			if(sysUserSession != null){
////				sysUserSession.setStatus(-1);//打上删除标记
////				sysUserSessionService.save(sysUserSession);
////			}
////			
////		} catch (CoreException e) {
////			e.printStackTrace();
////			throw new RuntimeException(e.getCode() + "--" +e.getMessage());
////		}
//	}
//
//	@Override
//	public Collection<Session> getActiveSessions() {
//		return super.getActiveSessions();
//	}
//
//	@Override
//	protected Serializable doCreate(Session session) {
////		Serializable sessionid =  super.doCreate(session);
////		String sid = sessionid.toString();
////		SysUserSession sysUserSession = new SysUserSession();
////		sysUserSession.setSessionid(sid);
////		sysUserSession.setIp(session.getHost());
////		sysUserSession.setStatus(1);
////		try {
////			sysUserSessionService.save(sysUserSession);
////		} catch (CoreException e) {
////			e.printStackTrace();
////			throw new RuntimeException(e.getCode() + "--" +e.getMessage());
////		}
//		return super.doCreate(session);
//	}
//
//	@Override
//	protected Session doReadSession(Serializable sessionId) {
////		System.out.println("doReadSession"+sessionId);
//		return super.doReadSession(sessionId);
//	}
//	
}
