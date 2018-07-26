package my.net;

import java.net.*;
import java.io.*;
//이미지 파일을 읽어 파일로 저장.

class  URLTest2
{
	public static void main(String[] args) 
		throws MalformedURLException, IOException
	{
		String urlStr
="http://cbingoimage.naver.com/data3/bingo_47/imgbingo_29/shk35ab/32721/shk35ab_3.jpg";
		URL url=new URL(urlStr);
		
		InputStream is=url.openStream();
		BufferedInputStream bis
			=new BufferedInputStream(is);

		//스트림 얻어서 파일로 저장하자. myimg.jpg
		FileOutputStream fos=new FileOutputStream("myimg.jpg");
		byte ba[]=new byte[1024];
		int n=0;
		int count=0;
		URLConnection uc=url.openConnection();
		int fileSize=uc.getContentLength();
		System.out.println("파일 크기: "+fileSize);
		System.out.println("컨텐트 타입: "+uc.getContentType());
		while((n=bis.read(ba))!=-1){
			fos.write(ba,0,n);
			count+=n;
			fos.flush();
			System.out.print(((count*100)/fileSize)+"% ");
		}//while----------
		fos.close();
		bis.close();
		is.close();



	}
}
