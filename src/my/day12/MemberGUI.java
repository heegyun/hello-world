package my.day12;


import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class MemberGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField tfNo = null;
	private JTextField tfId = null;
	private JTextField tfName = null;
	private JTextField tfTel = null;
	private JTextField tfAddr = null;
	private JScrollPane sp = null;
	private JTable table = null;
	private JPanel p = null;
	private JButton btAdd = null;
	private JButton btFind = null;
	private JButton btAll = null;
	private JButton btDel = null;
	private JButton btCancel = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	
	/**���� �߰��� �������********/
	MemberDAO dao=new MemberDAO();  
	DefaultTableModel model
				=new DefaultTableModel();
	
	public static final int NONE=0;
	public static final int ADD=1;
	public static final int DEL=2;
	public static final int FIND=3;
	public static final int ALL=4;
	
	int cmd=NONE;
	
	
	
	

	/**
	 * This is the default constructor
	 */
	public MemberGUI() {
		super();
		initialize();
		///////////////////
		try {
			dao.dbConnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//db�� Ŀ�ؼ�
		//////////////////
		model.addColumn("ȸ����ȣ");
		model.addColumn("���̵�");
		model.addColumn("��  ��");
		model.addColumn("��ȭ��ȣ");
		model.addColumn("��  ��");
		
		//model�� view�� ����
		table.setModel(model);
		table.getTableHeader().setBackground(
				Color.black);
		table.getTableHeader().setForeground(
				Color.yellow);
table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(20);
		initialTf();
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dao.close();
			
				//db�� ����� �ڿ� �ݳ�
				System.exit(0);
			}
		});
		
	}//������-----
	public void initialTf(){
		//tf���� ��Ȱ��ȭ
		boolean b=false;
		tfNo.setEditable(b);
		tfId.setEditable(b);
		tfName.setEditable(b);
		tfTel.setEditable(b);
		tfAddr.setEditable(b);
		
	}//initialTf()--------
	public void setEditable(int n){
		//tf�� ���� ���� ���θ� �����ϴ� �޼ҵ�
		boolean b=false;
		switch(n){
		case ADD:
			tfId.setEditable(!b);
			tfName.setEditable(!b);
			tfTel.setEditable(!b);
			tfAddr.setEditable(!b);			
			break;
		case FIND://�̸����� �˻�
			tfName.setEditable(!b);
			break;
		case DEL:// ���̵�� ����
			tfId.setEditable(!b);
			break;
		case NONE:
		case ALL:
			initialTf();
			break;
		}
		
	}//setEditable()---------
	
	/**��ư�� Ȱ��ȭ ���θ� �����ϴ� �޼ҵ�*/
	public void setEnabled(int n){
		boolean b=false;
		this.intialBt(b);
		switch(n){
		case ADD:
			btAdd.setEnabled(!b);
			btCancel.setEnabled(!b);
			cmd=ADD;
			break;
		case DEL:
			btDel.setEnabled(!b);
			btCancel.setEnabled(!b);
			cmd=DEL;
			break;
		case FIND:
			btFind.setEnabled(!b);
			btCancel.setEnabled(!b);
			cmd=FIND;
			break;
		case ALL:
			btAll.setEnabled(!b);
			btCancel.setEnabled(!b);
			cmd=ALL;
			break;
			
		case NONE:
			this.intialBt(!b);//��� ��ư Ȱ��ȭ
			break;
		}
		this.setEditable(cmd);
		//tf�� Ȱ��ȭ ���� ����..
		
	}
	/**��ư ��Ȱ��ȭ �޼ҵ�*/
	public void intialBt(boolean b){
		btAdd.setEnabled(b);
		btDel.setEnabled(b);
		btAll.setEnabled(b);
		btFind.setEnabled(b);
		btCancel.setEnabled(b);		
	}
	/**tf�� ����ִ� �޼ҵ�*/
	public void clearTf(){
		tfNo.setText("");
		tfId.setText("");
		tfName.setText("");
		tfTel.setText("");
		tfAddr.setText("");
	}
	
	public void add(){
		//����ڰ� �Է��� �� �޾ƿ���
		String id=tfId.getText();
		String name=tfName.getText();
		String tel=tfTel.getText();
		String addr=tfAddr.getText();
		String msg="";
		//��ȿ�� üũ
		if(id==null||name==null
				||id.trim().equals("")
				||name.trim().equals("")){
			msg="ID�� Name�� �Է��ϼ���";
			JOptionPane.showMessageDialog(this,
					msg);
			return;
		}//if-------
		//db�� �Է�
		int n=this.dao.insertMember(id, name,
								tel, addr);
		if(n>0){
			msg="ȸ�� ���� ����!";
		}else{
			msg="ȸ�� ���� ����";
		}
		JOptionPane.showMessageDialog(this, msg);
		showData(ALL);
	
	}//add()-------------------
	
	/**ID�� ȸ������ �����ϴ� �޼ҵ�*/
	public void delete(){
		String id=tfId.getText();
		String message="";
		if(id==null||id.trim().equals("")){
			message="���̵� �Է��ؾ� �մϴ�.";
			tfId.requestFocus();
			JOptionPane.showMessageDialog(this,
							message);
			return;
		}//if------
		message="���� "+id+"���� ������" +
				"�����Ͻðڽ���?";
		
		int yn
		=JOptionPane.showConfirmDialog(this, 
				message);
		if(yn==JOptionPane.YES_OPTION){
			int isDel=dao.deleteMember(id.trim());
			if(isDel>0){
				message="���� ����";
				showData(ALL);
			}else{
				message="���� ����";								
			}
			JOptionPane.showMessageDialog(this,
					message);
		}//if----------------
		
		
	}
	
	/**DAO�� �����͸� �޾ƿ� ���̺�
	 * �����ִ� �޼ҵ�*/
	public void showData(int n){
		MemberDTO arr[]=null;
		if(n==ALL){//��� ����
			arr=dao.selectAll();
		}else if(n==FIND){//�̸����� �˻�
			String name=tfName.getText();
			arr=dao.selectByName(name);						
		}
		if(arr==null||arr.length==0){
			JOptionPane.showMessageDialog(this,
					"���� ��ϵ� ȸ�� �����");
			return;
		}
		String colName[]
		        ={"ȸ����ȣ","���̵�",
				"�̸�","��ȭ��ȣ","�ּ�"};
		String[][]data
		=new String[arr.length][5];
		for(int i=0;i<data.length;i++){
			data[i][0]=arr[i].getNo()+"";
			data[i][1]=arr[i].getId();
			data[i][2]=arr[i].getName();
			data[i][3]=arr[i].getTel();
			data[i][4]=arr[i].getAddr();
		}//for----------
		model.setDataVector(data, colName);
		table.setModel(model);
	
	}//showData()--------
	
	
	
	/**
	 * This method initializes jLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel() {
		if (jLabel == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(12, 9, 84, 22));
			jLabel.setText("NO");
		}
		return jLabel;
	}
	/**
	 * This method initializes jLabel1	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(11, 72, 102, 24));
			jLabel1.setText("ID");
		}
		return jLabel1;
	}
	/**
	 * This method initializes jLabel2	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(12, 130, 88, 28));
			jLabel2.setText("NAME");
		}
		return jLabel2;
	}
	/**
	 * This method initializes jLabel3	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(11, 193, 94, 24));
			jLabel3.setText("TEL");
		}
		return jLabel3;
	}
	/**
	 * This method initializes jLabel4	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(16, 256, 85, 23));
			jLabel4.setText("ADDR");
		}
		return jLabel4;
	}
	public static void main(String args[]){
		new MemberGUI();
	}//main()------------

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(523, 380);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getTfNo(), null);
			jContentPane.add(getTfId(), null);
			jContentPane.add(getTfName(), null);
			jContentPane.add(getTfTel(), null);
			jContentPane.add(getTfAddr(), null);
			jContentPane.add(getSp(), null);
			jContentPane.add(getP(), null);
			jContentPane.add(getJLabel(), null);
			jContentPane.add(getJLabel1(), null);
			jContentPane.add(getJLabel2(), null);
			jContentPane.add(getJLabel3(), null);
			jContentPane.add(getJLabel4(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes tfNo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfNo() {
		if (tfNo == null) {
			tfNo = new JTextField();
			tfNo.setBounds(new Rectangle(11, 38, 122, 23));
			tfNo.setText("");
		}
		return tfNo;
	}

	/**
	 * This method initializes tfId	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setBounds(new Rectangle(11, 99, 122, 23));
			tfId.setText("");
		}
		return tfId;
	}

	/**
	 * This method initializes tfName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBounds(new Rectangle(11, 160, 122, 23));
			tfName.setText("");
		}
		return tfName;
	}

	/**
	 * This method initializes tfTel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfTel() {
		if (tfTel == null) {
			tfTel = new JTextField();
			tfTel.setBounds(new Rectangle(11, 221, 122, 23));
		}
		return tfTel;
	}

	/**
	 * This method initializes tfAddr	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTfAddr() {
		if (tfAddr == null) {
			tfAddr = new JTextField();
			tfAddr.setBounds(new Rectangle(11, 282, 122, 23));
		}
		return tfAddr;
	}

	/**
	 * This method initializes sp	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSp() {
		if (sp == null) {
			sp = new JScrollPane();
			sp.setBounds(new Rectangle(162, 15, 316, 218));
			sp.setViewportView(getTable());
		}
		return sp;
	}

	/**
	 * This method initializes table	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					System.out.println("mousePressed()"); // TODO Auto-generated Event stub mousePressed()
					int row=table.getSelectedRow();
					setTitle(row+"��");
					for(int i=0;i<5;i++){
						Object obj
						=table.getValueAt(row, i);
						String objStr=obj.toString();
						switch(i){
						case 0:
							tfNo.setText(objStr);
							break;
						case 1:
							tfName.setText(objStr);
							break;
						case 2:
							tfId.setText(objStr);
							break;
						case 3:
							tfTel.setText(objStr);
							break;
						case 4:
							tfAddr.setText(objStr);
							break;
							
						}//switch---------
						
					}//for----------
				}
			});
		}
		return table;
	}

	/**
	 * This method initializes p	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP() {
		if (p == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			p = new JPanel();
			p.setLayout(gridLayout);
			p.setBounds(new Rectangle(135, 267, 366, 64));
			p.add(getBtAdd(), null);
			p.add(getBtFind(), null);
			p.add(getBtAll(), null);
			p.add(getBtDel(), null);
			p.add(getBtCancel(), null);
		}
		return p;
	}

	/**
	 * This method initializes btAdd	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtAdd() {
		if (btAdd == null) {
			btAdd = new JButton();
			btAdd.setText("ADD");
			btAdd.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(cmd!=ADD){
						setEnabled(ADD);
						tfId.requestFocus();//Ŀ��
						
					}else{
						add();
						setEnabled(NONE);
						cmd=NONE;
						initialTf();
						clearTf();
					}
				}
			});
		}
		return btAdd;
	}

	/**
	 * This method initializes btFind	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtFind() {
		if (btFind == null) {
			btFind = new JButton();
			btFind.setText("FIND");
			btFind.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(cmd!=FIND){
						setEnabled(FIND);
						tfName.requestFocus();
					}else{
						showData(FIND);
						cmd=NONE;
						setEnabled(cmd);
						initialTf();
						clearTf();
						
					}
					
				}
			});
		}
		return btFind;
	}

	/**
	 * This method initializes btAll	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtAll() {
		if (btAll == null) {
			btAll = new JButton();
			btAll.setText("ALL");
			btAll.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					cmd=ALL;
					setEnabled(cmd);
					initialTf();
					showData(ALL);
				}
			});
		}
		return btAll;
	}

	/**
	 * This method initializes btDel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtDel() {
		if (btDel == null) {
			btDel = new JButton();
			btDel.setText("DELETE");
			btDel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(cmd!=DEL){
						setEnabled(DEL);
					}else{
						delete();//id�� ����
						setEnabled(NONE);
						cmd=NONE;
						initialTf();
						clearTf();
					}
				}
			});
		}
		return btDel;
	}

	/**
	 * This method initializes btCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtCancel() {
		if (btCancel == null) {
			btCancel = new JButton();
			btCancel.setText("CANCEL");
			btCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					cmd=NONE;
					setEnabled(cmd);
					initialTf();
				}
			});
		}
		return btCancel;
	}

}  //  @jve:decl-index=0:visual-constraint="18,0"
