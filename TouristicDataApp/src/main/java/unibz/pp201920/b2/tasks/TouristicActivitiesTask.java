package unibz.pp201920.b2.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unibz.pp201920.b2.constants.ResourceFile;
import unibz.pp201920.b2.datamanipulation.analysis.ActivitiesAnalyzer;
import unibz.pp201920.b2.datamanipulation.analysis.Analysis;
import unibz.pp201920.b2.datamanipulation.extraction.OpenDataHubTourism;
import unibz.pp201920.b2.datamanipulation.extraction.UserConfig;
import unibz.pp201920.b2.datamanipulation.transformation.Activity;
import unibz.pp201920.b2.datamanipulation.transformation.ActivityDeserializer;
import unibz.pp201920.b2.datamanipulation.transformation.JsonSerializer;
import unibz.pp201920.b2.exceptions.HttpRequestException;
import unibz.pp201920.b2.exceptions.InvalidJsonException;
import unibz.pp201920.b2.exceptions.InvalidLineException;
import unibz.pp201920.b2.exceptions.InvalidPathException;
import unibz.pp201920.b2.main.App;

import java.util.HashSet;
import java.util.Set;

/**
 * Facade class responsible for fetching touristic data, transforming it and analyzing it.
 * This task does not require any user interaction.
 *
 * @author Gioele De Vitti, Samuel Dalvai, Filippo Cenacchi
 */
public class TouristicActivitiesTask implements Task {

    private static final Logger logger = LogManager.getLogger();

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        logger.info("Reading input file");
        UserConfig config = readConfig();

        logger.info("Fetching touristic data from the OpenDataHub");
        JsonNode root = fetch(config.getNumberObjects());

        logger.info("Deserializing activities from json string");
        Set<Activity> activities = deserialize(root);

        logger.info("Serializing activities into json files");
        serializeActivities(activities);

        logger.info("Analyzing activities");
        Analysis analysis = analyze(activities);

        logger.info("Serializing analysis result into json file");
        serializeAnalysis(analysis);

        logger.info("Operation complete");
    }

    /**
     * Reads the user config.
     * Shuts down in case of error.
     *
     * @return A new instance of {@link UserConfig}, whose fields contain the loaded values.
     */
    private UserConfig readConfig() {
        UserConfig config = new UserConfig();

        try {
            config.loadFrom(ResourceFile.USER_CONFIG);
        } catch (InvalidLineException e) {
            logger.error("User config " + ResourceFile.USER_CONFIG.getFilepath() + " could not be loaded", e);
            App.shutdown(1);
        }
        logger.debug("Number of objects to retrieve from the web API: {}", config::getNumberObjects);

        return config;
    }

    /**
     * Fetches activities from the Open Data Hub.
     * Shuts down in case of error.
     *
     * @param count How many activities should be fetched.
     * @return The root {@link JsonNode}, whose field "Items" contains the activities.
     */
    private JsonNode fetch(int count) {
        OpenDataHubTourism response = OpenDataHubTourism.create(OpenDataHubTourism.Endpoint.ACTIVITY, count);

        JsonNode root = null;
        try {
            root = response.fetch();
        } catch (JsonProcessingException | HttpRequestException e) {
            logger.warn("Data could not be fetched from the OpenDataHub. " +
                    "Perhaps the internet connection is very slow or absent?", e);
            App.shutdown(1);
        }

        return root;
    }

    /**
     * Deserializes the given {@link JsonNode} to a {@link Set} of activities.
     * Shuts down in case of error.
     *
     * @param root The root {@link JsonNode}, whose field "Items" containg the activities.
     * @return A {@link Set} containing the activities that have been deserialized.
     */
    private Set<Activity> deserialize(JsonNode root) {
        ActivityDeserializer deserializer = new ActivityDeserializer(root);
        Set<Activity> activities = new HashSet<>();

        try {
            activities = deserializer.deserialize();
        } catch (InvalidJsonException e) {
            logger.error("Malformed json could not be deserialized to a list of activities.", e);
            App.shutdown(1);
        }
        logger.debug("Number of deserialized activities: {}", activities::size);

        return activities;
    }

    /**
     * Serializes the given activities into {@code .json} files.
     *
     * @param activities The activities that have to be serialized into {@code .json} files.
     */
    private void serializeActivities(Set<Activity> activities) {
        try {
            JsonSerializer serializer = new JsonSerializer(ResourceFile.JSONSCHEMA_ACTIVITY);
            serializer.serialize(activities, "results", false);
        } catch (InvalidPathException | InvalidJsonException e) {
            logger.error("Activities could not be serialized to json file", e);
            App.shutdown(1);
        }
    }

    /**
     * Analyzes the properties of the given activities and returns the result as a new instance of {@link Analysis}.
     *
     * @param activities The activities to be analyzed.
     * @return The result of the analysis as a new instance of {@link Analysis}.
     */
    private Analysis analyze(Set<Activity> activities) {
        ActivitiesAnalyzer analyzer = new ActivitiesAnalyzer(activities);
        return analyzer.analyze();
    }

    /**
     * Serializes the given {@link Analysis} into a {@code .json} file.
     *
     * @param analysis The analysis to be serialized.
     */
    private void serializeAnalysis(Analysis analysis) {
        try {
            JsonSerializer serializer = new JsonSerializer(ResourceFile.JSONSCHEMA_ANALYSIS);
            serializer.serialize(analysis, "results", true);
        } catch (InvalidPathException | InvalidJsonException e) {
            logger.error("Analysis could not be serialized to json file");
            App.shutdown(1);
        }
    }

}
