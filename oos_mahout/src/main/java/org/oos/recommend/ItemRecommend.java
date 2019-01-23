package org.oos.recommend;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Service;

@Service
public class ItemRecommend {
	
	
	public List<RecommendedItem> itemRec(int mno) {
		

	try {
		DataModel dm = new FileDataModel(new File("intro.csv"));
			ItemSimilarity sim = new PearsonCorrelationSimilarity(dm);

			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);

			List<RecommendedItem> recommendations = recommender.mostSimilarItems(mno, 5);
			
			return recommendations;
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return null;
			
	}
}
