package kl;

/**

 * @author liuyu
 * ��ʵ����Ϊÿ���ַ��һ����λ
 *
 */
public class Entity
{
	String word;//�洢�ַ�
	double pValue;//�洢���ַ��Ӧ�ĸ���ֵ
	public Entity()//��Ĺ��캯��
	{  
		pValue=0;
		word="";
		
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public double getpValue() {
		return pValue;
	}
	public void setpValue(double pValue) {
		this.pValue = pValue;
	}

}
