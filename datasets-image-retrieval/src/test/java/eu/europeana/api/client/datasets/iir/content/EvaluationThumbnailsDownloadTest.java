package eu.europeana.api.client.datasets.iir.content;

import java.io.FileNotFoundException;
import java.io.IOException;

import eu.europeana.api.client.dataset.download.DownloadThumbnailsTest;

public class EvaluationThumbnailsDownloadTest extends DownloadThumbnailsTest{

	@Override
	protected void ensureParamsInit() {
		setDataset(EvaluationDatasetBuilderTest.DATASET_DEMO);
		super.ensureParamsInit();
	}
	
	//@Test
	public void downloadThumbnails() throws FileNotFoundException, IOException{
		super.downloadThumbnails();
	}
}
