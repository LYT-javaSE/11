package lifeGame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	private static int maxWidth = 20;
	private static int maxLength = 35;
	private static Cell cell=new Cell(maxLength,maxWidth);
	
	//��ÿ�����Է���֮ǰִ��
	@Before
	public void setUp() throws Exception {
		cell.deleteAllCell();
		cell.setNowGeneration(0);
		cell.getNeighborCount(maxLength, maxWidth);
	}
	
	//��ǰ���Է���Ϊ RandomCell ����
	@Test
	public void testRandomCell() {
		boolean flag=false;  //�ȳ�ʼ�� flag Ϊ false
		cell.randomCell();
		for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)//�������飬��������ͼ��ÿһ������
            	if(cell.getGrid()[i][j]!=0)
            		flag=true;                //�����ͼ�д��ڷ���ϸ��������ϸ����flag=true
		assertEquals(true,flag);       //�鿴���������Ƿ���ȣ����� flag �Ƿ�Ϊ true,Ҳ�� ���Ƿ���ڻ�ϸ�������л�ϸ�����Խ��Ϊ�ɹ�   	
	}
	
	//��ǰ���Է���Ϊ DeleteAllCell ����
	@Test
	public void testDeleteAllCell() {
		for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
            	assertEquals(0,cell.getGrid()[i][j]);//�鿴���������Ƿ���ȣ�������ͼ�� �ǲ��Ƕ�����ϸ������������ϸ������Խ��Ϊ�ɹ�
	}
	
	//��ǰ���Է���Ϊ Update ����
	@Test
	public void testUpdate() {
		int flag=0;
		int[][] newGrid = new int[maxLength + 2][maxWidth + 2];//ѡȡ�Ź�
		
		//�ٵ�һ�����ݣ��ھ���Ϊ 0��ϸ��ȫΪ�� 
		//�������飬��������ͼ��ÿһ������
		for (int i = 0; i <= maxLength+1; i++)
            for (int j = 0; j <= maxWidth+1; j++)
                newGrid[i][j] = 0;   
		cell.setGrid(newGrid);
		assertEquals(0,cell.getNeighborCount(maxLength, maxWidth));//�����ھ���Ϊ 0
		cell.update();
		for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                if(cell.getGrid()[i][j]!=newGrid[i][j])
                	flag=1;
		assertEquals(0,flag);   //�鿴���������Ƿ���ȣ�������ͼ�ϵ���ϸ��һ��֮���� ������ϸ������������ϸ������Խ��Ϊ�ɹ�
		
		
		//�ڵڶ������ݣ��ھ���Ϊ 2
		newGrid[9][8]=0;
		newGrid[9][7]=1;
		newGrid[9][9]=1;
		cell.setGrid(newGrid);
		cell.update();
		//newGrid[9][8]=0;//�ٶ����ֲ���
		assertEquals(2,cell.getNeighborCount(maxLength, maxWidth));//�����ھ���Ϊ 2
		for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                if(cell.getGrid()[i][j]!=newGrid[i][j])//����ͼ��ϸ�������任����flag=1
                	flag=1;
		assertEquals(0,flag);  //�鿴���������Ƿ���ȣ�ϸ��������״̬��Ӧ�ñ��ֲ���ģ���֪��ͼ��ϸ��δ�����任���� flag ����Ϊ 0������Խ��Ϊ�ɹ�
		
		for (int i = 0; i <= maxLength+1; i++)
            for (int j = 0; j <= maxWidth+1; j++)
                newGrid[i][j] = 0;//������ʼ����ʹϸ��ȫΪ��
		
		//�۵��������ݣ��ھ���Ϊ 3
		newGrid[10][12]=1;
		newGrid[10][14]=1;
		newGrid[12][12]=1;
		cell.setGrid(newGrid);
		cell.update();
		newGrid[11][13]=1;//�ٶ�Ϊ�����������Ƿ����
		assertEquals(3,cell.getNeighborCount(maxLength, maxWidth));//�����ھ���Ϊ 3
		for (int i = 1; i <= maxLength; i++)
            for (int j = 1; j <= maxWidth; j++)
                if(cell.getGrid()[i][j]!=newGrid[i][j])//����ͼ��ϸ�������ڰ��չ���ó����µ� newGrid[][],�� flag=1
                	flag=1;
		assertEquals(0,flag);//�鿴���������Ƿ���ȣ�ϸ��������״̬��Ӧ�Ǳ�����ģ�����ͼ��ϸ���������任���� flag ����Ϊ 0������Խ��Ϊ�ɹ�
		
	}

}

