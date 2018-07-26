package my.day13.swing;

import javax.swing.*;
import javax.swing.table.*;
/**
고유한 JTable의 모델 만들기
*/
public class  MyTableModel extends AbstractTableModel{
Object data[][]={  //Model파트-data를 가지는 부분
{"홍길동", new Integer(1), new ImageIcon("./img/img01.gif"), new Boolean(true) },
{"김길동", new Integer(2), new ImageIcon("./img/img02.gif"), new Boolean(false) },
{"정길동", new Integer(3), new ImageIcon("./img/img03.gif"), new Boolean(true) },
{"강길동", new Integer(4), new ImageIcon("./img/img04.gif"), new Boolean(false) }
};
String[] colNames={"이름","번호","캐릭터","사람인가?"};

//컬럼수를 리턴해줘야...오버라이딩 필수..
public int getColumnCount(){
	return colNames.length;
}
//행의 수를 리턴해줘야...오버라이딩 필수
public int getRowCount(){
	return data.length;
}
//각 행과 열의 해당되는 객체를 리턴...오버라이딩 필수
public Object getValueAt(int row, int col){
		return data[row][col];
}

//필수는 아니지만 옵션인 메소드 오버라이딩
//컬럼명을 반환하는 메소드
public String getColumnName(int col){
	return colNames[col];
}

//옵션-각 컬럼의 클래스 객체를 리턴--
public Class getColumnClass(int col){
	Object obj=getValueAt(0, col);
	Class cl=obj.getClass();
	return cl;
}
//옵션--각 셀이 편집 가능하게 해주려면 오버라이딩
public boolean isCellEditable(int row, int col){
	boolean res=(col==1)? false: true;
		//번호는 편집 불가능하게 만들자. 나머지는 편집 가능
	return res;
}
//옵션--수정된 값을 적용시키려면 오버라이딩.
public void setValueAt(Object v, int row, int col){
	String sval=v.toString();
	switch (col)
	{		
	case 0://1열 인 경우...String객체
		data[row][col]=sval;
		break;
	case 1://2열인 경우...Integer객체
		data[row][col]=new Integer(sval);
		break;
	case 2://3열인 경우...ImageIcon객체
		data[row][col]=new ImageIcon(sval);
		break;
	case 3://4열인 경우...Boolean
		data[row][col]=new Boolean(sval);
		break;		
	}

}//-------------------



}/////////////////////////////
