package eu.europeana.api.client.datasets.iir.content;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import eu.europeana.api.client.dataset.download.DownloadThumbnailsTest;

public class EvaluationThumbnailsDownloadTest extends DownloadThumbnailsTest{

	@Override
	//@Before
	protected void ensureParamsInit() {
		setDataset(EvaluationDatasetBuilderTest.DATASET_DEMO);
		super.ensureParamsInit();
	}
	
	//@Test
	public void downloadThumbnails() throws FileNotFoundException, IOException{
		super.downloadThumbnails();
	}
	
	//@Test
	public void filterPlaceholders() throws FileNotFoundException, IOException, URISyntaxException{
		ensureParamsInit();
		//super.downloadThumbnails();
		File datasetFile = getDatasetFile();
		File downloadFolder = getDatasetImageFolder();
		Map<String, String> thumbnails = readThumbnailsMap(datasetFile);
		File imageFile;
		URL placeHolderLocation = getClass().getResource("/image.png");
		File imagePlaceHolder = new File(placeHolderLocation.toURI());
		
		Map<String, String> placeHolderMap = new HashMap<String, String>();
		
		for (Map.Entry<String, String> entry : thumbnails.entrySet()) {
			imageFile = new File(downloadFolder, entry.getKey() + ".jpg");
			if(imagePlaceHolder.length() == imageFile.length())
				placeHolderMap.put(entry.getKey(), entry.getValue());
		}

		if(!placeHolderMap.isEmpty())
			writeContentMapToFile(placeHolderMap, new File("/tmp/solrlire/placeholders.csv"));
		else 
			System.out.println("No Placeholder files found");
	}
	
	@Test
	public void filterExcludedItems() throws FileNotFoundException, IOException, URISyntaxException{
		ensureParamsInit();
		//super.downloadThumbnails();
		File datasetFile = getDatasetFile();
		String datasetExclude = datasetFile.getAbsolutePath().replace(".csv", "_exclude.csv");
		File datasetExcludeFile = new File(datasetExclude);
		
		Map<String, String> datasetItems = readThumbnailsMap(datasetFile);
		Map<String, String> excludedItems = readThumbnailsMap(datasetExcludeFile);
		
		Map<String, String> finalDatasetItems = new LinkedHashMap<String, String>();
		
		for (Map.Entry<String, String> entry : datasetItems.entrySet()) {
			if(!excludedItems.containsKey(entry.getKey()))
				finalDatasetItems.put(entry.getKey(), entry.getValue());
			else 
				System.out.println("excluded item: " + entry.getKey());
		}

		String finalDataset = datasetFile.getParent() + "/final/"+ datasetFile.getName();
		File finalDatasetFile = new File(finalDataset);
		
		finalDatasetFile.getParentFile().mkdirs();
		writeContentMapToFile(finalDatasetItems, finalDatasetFile);
		
	}
	
}
