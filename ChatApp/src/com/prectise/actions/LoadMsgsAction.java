package com.prectise.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.prectise.ICommons.ICommons;
import com.prectise.logic.IAction;
import com.prectise.model.bean.Message;
import com.prectise.model.dao.FactoryDAO;
import com.prectise.model.dao.FactoryDAO.eDB;
import com.prectise.model.dao.logic.IMsgDAO;

//https://kodejava.org/how-do-i-convert-collections-into-json/
public class LoadMsgsAction implements IAction,ICommons{

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) {
		
		FactoryDAO factoryDAO = FactoryDAO.getFactory(eDB.MySQL);
		IMsgDAO MsgDAO = factoryDAO.getMsgDAO();
		String blockNumber = request.getParameter(PARAM_BLOCK_NUMBER);
		if(blockNumber == null){
			blockNumber = "0";
		}
		List<Message> messages = MsgDAO.getBlockMessages(Integer.valueOf(blockNumber));
		
		try{
			Gson gson = new Gson();
			String jsonMessages = gson.toJson(messages);
			response.setContentType("application/json");
			response.getWriter().write(jsonMessages);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return LoadMsgsAction.class.getSimpleName();
	}

}
