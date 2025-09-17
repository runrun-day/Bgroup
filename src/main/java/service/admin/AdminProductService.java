package service.admin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import dao.ProductDAO;
import model.Product;

public class AdminProductService {
	//ここのフォルダのパスを環境に合わせて変更する c/[プロジェクト名]/images/uploads
		public static final String FILE_PATH = "C:/Btest/images/uploads";
		
		ProductDAO dao = new ProductDAO();
		
		//ファイルを仮領域へコピー
		//引数:HttpServletRequest request リクエストオブジェクト
		//     String tempPath   仮領域のパス
		//戻り値:newFileName リネームしたファイル名
		public String upload(HttpServletRequest request, String tempPath) {
			String uploadName;
			// 一時保存
			Part part;
			try {
				part = request.getPart("image");
				uploadName = part.getSubmittedFileName(); // 元ファイル名
				File uploadDir = new File(tempPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}

				String filePath = tempPath + File.separator + uploadName;
				part.write(filePath);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} catch (ServletException e) {
				e.printStackTrace();
				return null;
			}
			return uploadName;
		}
		
		public String move(String tempPath, String uploadName) {

			// 保存フォルダ
			String saveDir = AdminProductService.FILE_PATH;
			File uploadDir = new File(saveDir);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs(); // ← mkdirs() は親フォルダも作成
			}

			// UUIDでリネーム
			String ext = "";
			//拡張子を取得する
			int dot = uploadName.lastIndexOf(".");
			if (dot != -1) {
				ext = uploadName.substring(dot);
			}
			//UUID.randomUUID()で重複しないIDを生成し、ファイル名にする
			String newFileName = UUID.randomUUID().toString() + ext;

			// 一時ファイルを本保存場所へ移動
			File tempFile = new File(tempPath, uploadName);
			File destFile = new File(saveDir, newFileName);

			// コピー
			try (java.io.InputStream in = new java.io.FileInputStream(tempFile);
					java.io.OutputStream out = new java.io.FileOutputStream(destFile)) {
				in.transferTo(out);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			// コピーが成功したら一時ファイルを削除
			tempFile.delete();

			return newFileName;
		}			
		
		//登録
		public boolean create(Product product) {
			// 簡単な入力チェック
			if (product == null) {
				return false;
			}
			return dao.create(product);
		}
}
