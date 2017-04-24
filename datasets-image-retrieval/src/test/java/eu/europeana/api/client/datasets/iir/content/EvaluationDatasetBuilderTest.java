package eu.europeana.api.client.datasets.iir.content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.europeana.api.client.config.ClientConfiguration;
import eu.europeana.api.client.config.EuropeanaApiConfiguration;
import eu.europeana.api.client.dataset.DatasetDescriptor;
import eu.europeana.api.client.datasets.commons.config.DatasetsConfiguration;
import eu.europeana.api.client.exception.EuropeanaApiProblem;
import eu.europeana.api.client.model.search.CommonMetadata;
import eu.europeana.api.client.model.search.EuropeanaApi2Item;
import eu.europeana.api.client.search.query.Api2QueryInterface;
import eu.europeana.api.client.thumbnails.ThumbnailsForCollectionAccessorTest;

/**
 * 
 * @author Sergiu Gordea
 *
 */
public class EvaluationDatasetBuilderTest extends ThumbnailsForCollectionAccessorTest {

	public final static String DATASET_DEMO = "demo";
	private String dataset = null;

	public static String CLASS_ART = "art";
	public static String CLASS_OBJECT = "object";
	public static String CLASS_FASHION = "fashion";

	// art subclasses
	public static String SUB_CLASS_PAINTING = "painting";
	public static String SUB_CLASS_PORTRAINTS = "portraits";
	public static String SUB_CLASS_POSTER = "poster";
	public static String SUB_CLASS_ILLUMINATED_MANUSCRIPT = "illuminatedManuscript";
	public static String SUB_CLASS_TAPESTRY = "tapestry";
	public static String SUB_CLASS_SCULPTURE = "sculpture";
	public static String SUB_CLASS_CARPET = "carpet";
	public static String SUB_CLASS_JAPAN_PRINT = "japanPrint";

	// object subclasses
	public static String SUB_CLASS_PORCELAIN = "porcelain";

	// object & fashion subclasses
	public static String SUB_CLASS_JEWELLERY = "jewellery";
	public static String SUB_CLASS_SHOES = "shoes";
	public static String SUB_CLASS_HEADGEAR = "headgear";
	public static String SUB_CLASS_DRAWING_ILLUSTRATION = "drawingIllustration";

	// object & music subclasses
	public static String SUB_CLASS_MUSIC_INSTRUMENT = "musicInstrument";

	@Test
	public void createDemoDataset() throws IOException, EuropeanaApiProblem {
		setDataset(DATASET_DEMO);

		/*
		 * 
		 * //1 buildArtPaintingImageSet();
		 * 
		 * // //2 buildArtPosterImageSet();
		 * 
		 * // //3 buildArtManuscriptImageSet();
		 * 
		 * // //4 buildArtTapestryImageSet();
		 * 
		 * // //5 buildArtSculptureImageSet();
		 * 
		 * // //6 buildArtCarpetImageSet();
		 * 
		 * // //7 buildArtJapanPrintImageSet();
		 * 
		 * // //8 buildObjectPorcelainImageSet();
		 * 
		 * // //9 buildObjectJewelleryImageSet();
		 * 
		 * // //10 buildObjectShoesImageSet();
		 * 
		 * // //11 buildObjectHeadGearImageSet();
		 * 
		 * // //12 buildFashionDrawingSet();
		 * 
		 * // //13 buildObjectMusicInstrumentImageSet(); //
		 * 
		 */
		// performDatasetAggregation();

//		generateImageIndexerInputFile();
	}

	// @Test
	public void buildArtPaintingImageSet() throws IOException, EuropeanaApiProblem {
		// art-painting
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_ART, SUB_CLASS_PAINTING,
				new String[] { CLASS_ART, SUB_CLASS_PAINTING });

		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3Apainting&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3Apainting&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 28100+, some ingestion affectes the numbers currently
		// expect at least 27k thumbnail urls
		Assert.assertTrue(27000 < objects0);
	}

	private void buildArtSculptureImageSet() throws IOException, EuropeanaApiProblem {
		// art-sculpture
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_ART, SUB_CLASS_SCULPTURE,
				new String[] { CLASS_ART, SUB_CLASS_SCULPTURE });

		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3A%28sculpture+OR+kulptur+OR+beeldhouwwerk+OR+sculptuur%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3A%28sculpture+OR+kulptur+OR+beeldhouwwerk+OR+sculptuur%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 11449, assert 11k
		Assert.assertEquals(11000, objects0);
	}

	private void buildArtCarpetImageSet() throws IOException, EuropeanaApiProblem {
		// art-carpet
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_ART, SUB_CLASS_CARPET,
				new String[] { CLASS_ART, SUB_CLASS_CARPET });

		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3A%28carpet+OR+tapijtOR+teppich+OR+matta%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3A%28carpet+OR+tapijtOR+teppich+OR+matta%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 871
		Assert.assertTrue(870 < objects0);
	}

	private void buildArtJapanPrintImageSet() throws IOException, EuropeanaApiProblem {
		// art-japan print
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_ART, SUB_CLASS_JAPAN_PRINT,
				new String[] { CLASS_ART, SUB_CLASS_JAPAN_PRINT });

		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=%28what%3Aprint+AND+where%3Ajapan%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=%28what%3Aprint+AND+where%3Ajapan%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 1648, asert 1600
		Assert.assertTrue(1600 < objects0);
	}

	private void buildArtPosterImageSet() throws IOException, EuropeanaApiProblem {
		// art-poster
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_ART, SUB_CLASS_POSTER,
				new String[] { CLASS_ART, SUB_CLASS_POSTER });

		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3Aposter&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3Aposter&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 13532
		// expect at least 13k thumbnails
		Assert.assertTrue(13000 < objects0);
	}

	private void buildArtManuscriptImageSet() throws IOException, EuropeanaApiProblem {
		// art-illuminated manuscript
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_ART, SUB_CLASS_ILLUMINATED_MANUSCRIPT,
				new String[] { CLASS_ART, SUB_CLASS_ILLUMINATED_MANUSCRIPT });

		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3A%22illuminated+manuscript%22&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3A%22illuminated+manuscript%22&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 20592, assert at least 20000
		Assert.assertTrue(20000 < objects0);
	}

	private void buildArtTapestryImageSet() throws IOException, EuropeanaApiProblem {
		// art-tapestry
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_ART, SUB_CLASS_TAPESTRY,
				new String[] { CLASS_ART, SUB_CLASS_TAPESTRY });

		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3A%28tapestry+OR+wandtapijt+ORbildwerkerei+OR+Gobel%C3%A4ng%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3A%28tapestry+OR+wandtapijt+ORbildwerkerei+OR+Gobel%C3%A4ng%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 228
		Assert.assertEquals(228, objects0);
	}

	void buildObjectPorcelainImageSet() throws IOException, EuropeanaApiProblem {
		// object-porcelain ceramics
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_OBJECT, SUB_CLASS_PORCELAIN,
				new String[] { CLASS_OBJECT, SUB_CLASS_PORCELAIN });
		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=proxy_dc_format%3A%22porselein%22+OR+proxy_dcterms_medium%3A%22Porcelain%22&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=proxy_dc_format%3A%22porselein%22+OR+proxy_dcterms_medium%3A%22Porcelain%22&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 443
		Assert.assertTrue(443 < objects0);
	}

	void buildObjectJewelleryImageSet() throws IOException, EuropeanaApiProblem {
		// object-jewellery
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_OBJECT, SUB_CLASS_JEWELLERY,
				new String[] { CLASS_OBJECT, SUB_CLASS_JEWELLERY });
		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3A%28jewellery+OR+sieraden+OR+schmuck+OR+smycke%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3A%28jewellery+OR+sieraden+OR+schmuck+OR+smycke%29&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&qf=TYPE%3AIMAGE&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 16910, assert 16k
		Assert.assertTrue(16000 < objects0);
	}

	void buildObjectShoesImageSet() throws IOException, EuropeanaApiProblem {
		// object-shoes
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_OBJECT, SUB_CLASS_SHOES,
				new String[] { CLASS_OBJECT, SUB_CLASS_SHOES });
		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3A%28shoes+OR+sandals+OR+pumps%29+AND+PROVIDER%3A%22Europeana+Fashion%22&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3A%28shoes+OR+sandals+OR+pumps%29+AND+PROVIDER%3A%22Europeana+Fashion%22&rows=25&start=1&thumbnail=true&media=true&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 8784, assert 8.7k
		Assert.assertTrue(8700 < objects0);
	}

	void buildObjectHeadGearImageSet() throws IOException, EuropeanaApiProblem {
		// object-headgear
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_OBJECT, SUB_CLASS_HEADGEAR,
				new String[] { CLASS_OBJECT, SUB_CLASS_HEADGEAR });
		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3Aheadgear+AND+PROVIDER%3A%22Europeana+Fashion%22+NOT+%22fashion+show%22&rows=25&start=1&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3Aheadgear+AND+PROVIDER%3A%22Europeana+Fashion%22+NOT+%22fashion+show%22&rows=25&start=1&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 2430, assert 2.4k
		Assert.assertTrue(2400 < objects0);
	}

	void buildFashionDrawingSet() throws IOException, EuropeanaApiProblem {
		// fashion-drawing illustration
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_FASHION, SUB_CLASS_DRAWING_ILLUSTRATION,
				new String[] { CLASS_FASHION, SUB_CLASS_DRAWING_ILLUSTRATION });
		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=what%3A%28sketch+OR+drawing+OR+%22fashion+illustration%22%29+AND+PROVIDER%3A%22Europeana+Fashion%22+NOT+%22fashion+show%22&rows=25&start=1&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=what%3A%28sketch+OR+drawing+OR+%22fashion+illustration%22%29+AND+PROVIDER%3A%22Europeana+Fashion%22+NOT+%22fashion+show%22&rows=25&start=1&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 10019, assert 10k
		Assert.assertTrue(10000 < objects0);
	}

	void buildObjectMusicInstrumentImageSet() throws IOException, EuropeanaApiProblem {
		// object-musical instrument
		DatasetDescriptor dataset = new DatasetDescriptor(CLASS_OBJECT, SUB_CLASS_MUSIC_INSTRUMENT,
				new String[] { CLASS_OBJECT, SUB_CLASS_MUSIC_INSTRUMENT });
		dataset.setThumbnailWithSize(CommonMetadata.EDM_FIELD_THUMBNAIL_W400);
		dataset.setStoreItemPreview(true);

		// http://www.europeana.eu/api/v2/search.json?query=PROVIDER%3A%22MIMO+-+Musical+Instrument+Museums+Online%22&rows=25&start=1&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&profile=facets&wskey=api2demo
		String searchApiUrl = "http://www.europeana.eu/api/v2/search.json?query=PROVIDER%3A%22MIMO+-+Musical+Instrument+Museums+Online%22&rows=25&start=1&qf=IMAGE_SIZE%3Amedium&qf=IMAGE_SIZE%3Alarge&qf=IMAGE_SIZE%3Aextra_large&qf=IMAGE_COLOUR%3Atrue&profile=facets&wskey=api2demo";
		Api2QueryInterface query = getQueryBuilder().buildBaseQuery(searchApiUrl);

		int objects0 = buildImageSet(dataset, query);
		// 4746, assert 4.7k
		Assert.assertTrue(4700 < objects0);
	}

	private void performDatasetAggregation() throws IOException {
		File cvsFolder = new File(getDatasetsOverviewCsvFolder());
		File[] collectionFiles = cvsFolder.listFiles();
		BufferedReader reader = null;
		// String headerLine = null;
		String line = null;
		BufferedWriter datasetWriter = getDataSetFileWriter(false);

		for (int i = 0; i < collectionFiles.length; i++) {
			reader = new BufferedReader(new FileReader(collectionFiles[i]));
			boolean firstLine = true;
			while ((line = reader.readLine()) != null) {
				// write headers to sysout
				if (firstLine) {
					System.out.println(line);
					firstLine = false;
				}
				// write all data to dataset
				datasetWriter.write(line);
				datasetWriter.write("\n");

			}
			datasetWriter.flush();
			// close reader
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("cannot close reader for: " + collectionFiles[i]);
				e.printStackTrace();
			}
		}
		datasetWriter.close();
	}

	public File getDataSetFile(boolean urls) {
		if (urls)
			return getConfig().getDatasetUrlsFile(getDataset());
		else
			return getConfig().getDatasetFile(getDataset());
	}

	@Ignore
	public void testGetThumbnailsForCollectionLimit() {
		// avoid execution
	}

	public void testGetThumbnailsForCollectionAll() {
		// avoid execution
	}

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	protected DatasetsConfiguration getConfig() {
		return DatasetsConfiguration.getInstance();
	}

	protected void generateImageIndexerInputFile() throws FileNotFoundException, IOException {

		File thumbnailsCsvFile = getDataSetFile(false);
		Map<String, String> idAndThumbnailUrlMap = readThumbnailsMap(thumbnailsCsvFile);
		File outFile = getDatasetOutFile("image_indexer_in");

		int limit = 100;
		int counter = 0;

		String datasetImageFolder = getConfig().getImageFolder(getDataset());
		File imageFolder = new File(datasetImageFolder);
		StringBuilder line;
		String id;
		File imageFile;
		char csvSeparator = ';';
		String title;

		if (!outFile.getParentFile().exists())
			outFile.getParentFile().mkdirs();

		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile, false));

		try {
			for (Map.Entry<String, String> idAndThumbnailUrl : idAndThumbnailUrlMap.entrySet()) {
				// TODO: avoid duplications
				line = new StringBuilder();
				id = idAndThumbnailUrl.getKey();

				// absolute path
				imageFile = getConfig().getImageFile(imageFolder, id);
				line.append(imageFile.getAbsolutePath()).append(csvSeparator);
				// id
				line.append(id).append(csvSeparator);
				// thumbnail Url
				line.append(idAndThumbnailUrl.getValue()).append(csvSeparator);

				// title
				title = getTitleFromObjectPreview(id);
				line.append(title).append("\n");
				writer.write(line.toString());
				
				// include only top(limit) items
				if (limit > 0 && counter++ == limit){
					writer.flush();
					break;
				}
				
				if(counter % 100 == 0 )
					writer.flush();
				
			}
		} finally {
			writer.close();
		}

	}

	private String getTitleFromObjectPreview(String id) throws IOException {

		String metadataFolder = getConfig().getMetadataFolder();
		String metadataFile = getConfig().getJsonMetadataFile(id, metadataFolder,
				EuropeanaApiConfiguration.REPRESENTATION_PREVIEW);
		System.out.println(metadataFile);
		File jsonFile = new File(metadataFile);

		Gson gson = new GsonBuilder().create();
		String json = FileUtils.readFileToString(jsonFile);
		EuropeanaApi2Item res = gson.fromJson(json, EuropeanaApi2Item.class);
		if (res != null && res.getTitle() != null && !res.getTitle().isEmpty())
			return res.getTitle().get(0);

		// else
		return null;
	}

	protected File getDatasetOutFile(String processingStep) {
		String datasetsFolder = getConfig().getDatasetsFolder();
		String datasetOutFile = datasetsFolder + "/" + processingStep + "/" + getDataset()
				+ ClientConfiguration.DATASET_FILE_EXTENSION;
		return new File(datasetOutFile);
	}
}
