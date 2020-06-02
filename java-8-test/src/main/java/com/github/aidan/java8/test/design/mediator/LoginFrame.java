package com.github.aidan.java8.test.design.mediator;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author wang yi fei
 * @date 2019/5/27 14:34
 */
public class LoginFrame extends Frame implements ActionListener,Mediator {
	private ColleagueCheckBox chexkGuest;
	private ColleagueCheckBox chexkLogin;
	private ColleagueTextField textUser;
	private ColleagueTextField textPass;
	private ColleagueButton buttonOk;
	private ColleagueButton buttonCancel;

	public LoginFrame(String title) throws HeadlessException {
		super(title);
		setBackground(Color.lightGray);
		//  使用布局管理器生成 4 X 2 窗格
		createColleagues();
		// 配置
		add(chexkGuest);
		add(chexkLogin);
		add(new Label("Username:"));
		add(textUser);
		add(new Label("Password:"));
		add(textPass);
		add(buttonOk);
		add(buttonCancel);
		//  设置初始的启用 / 禁用状态
		colleagueChanged();
		// 显示
		pack();
		setVisible(true);
	}

	@Override
	public void createColleagues() {
		// 生成
		CheckboxGroup g = new CheckboxGroup();
		chexkGuest = new ColleagueCheckBox("Guest",g,true);
		chexkLogin = new ColleagueCheckBox("Login",g,false);
		textUser = new ColleagueTextField("",10);
		textPass = new ColleagueTextField("",10);
		textPass.setEchoChar('*');
		buttonOk = new ColleagueButton("OK");
		buttonCancel = new ColleagueButton("Cancel");
		// 设置 Mediator
		chexkGuest.setMediator(this);
		chexkLogin.setMediator(this);
		textUser.setMediator(this);
		textPass.setMediator(this);
		buttonOk.setMediator(this);
		buttonCancel.setMediator(this);

		// 设置 Listener
		chexkGuest.addItemListener(chexkGuest);
		chexkLogin.addItemListener(chexkLogin);
		textUser.addTextListener(textUser);
		textPass.addTextListener(textPass);
		buttonOk.addActionListener(this);
		buttonCancel.addActionListener(this);
	}

	@Override
	public void colleagueChanged() {
		if (chexkGuest.getState()){
			textUser.setColleagueEnabled(false);
			textPass.setColleagueEnabled(false);
			buttonOk.setColleagueEnabled(false);
		}else {
			textUser.setColleagueEnabled(true);
			userpassChanged();
		}
	}

	private void userpassChanged(){
		if (textUser.getText().length() > 4){
			textPass.setColleagueEnabled(true);
			if (textPass.getText().length() > 4){
				buttonOk.setColleagueEnabled(true);
			}else {
				buttonOk.setColleagueEnabled(false);
			}
		}else {
			textPass.setColleagueEnabled(false);
			buttonOk.setColleagueEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.toString());
		System.exit(0);
	}
}
