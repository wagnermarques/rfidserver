package br.com.fzlbpms.service;
 
public interface Command<ParamType,ReturnType> {

	public ReturnType execute(ParamType param) throws Exception;
	
}
