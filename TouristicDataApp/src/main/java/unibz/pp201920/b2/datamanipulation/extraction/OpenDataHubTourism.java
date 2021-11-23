package unibz.pp201920.b2.datamanipulation.extraction;

import okhttp3.HttpUrl;

/**
 * Class {@code OpenDataHubTourism} delivers Json objects from the OpenDataHub web API,
 * which contains data about tourism in South Tyrol.
 * @see <a href="https://opendatahub.bz.it">https://opendatahub.bz.it</a> for more information on the OpenDataHub
 * project and <a href="https://opendatahub.readthedocs.io/en/latest">https://opendatahub.readthedocs.io/en/latest</a>
 * for the OpenDataHub documentation.
 * <a href="https://en.wikipedia.org/wiki/South_Tyrol">South Tyrol</a> is an autonomous province in northern Italy.
 *
 * @author Gioele De Vitti
 */
public final class OpenDataHubTourism extends JsonFetcher {

    public static final String scheme = "http";
    public static final String host = "tourism.opendatahub.bz.it";

    public enum Endpoint {
        ACTIVITY("Activity");

        private final String path;

        Endpoint(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return path;
        }
    }

    /**
     * Creates a {@code OpenDataHubTourism} object from the
     * specified {@code Endpoint}.
     *
     * @param endpoint the endpoint of the web API.
     * @param number   the number of objects to retrieve from the web API.
     */
    private OpenDataHubTourism(Endpoint endpoint, int number) {
        super(new HttpUrl.Builder()
                .scheme(scheme)
                .host(host)
                .addPathSegment("api")
                .addPathSegment(endpoint.toString())
                .addQueryParameter("pagesize", String.valueOf(number))
                .build());
    }

    /**
     * Factory method of {@code OpenDataHubTourism} that verifies the given parameters
     * before creating a new instance of {@code OpenDataHubTourism}.
     *
     * @param endpoint  the endpoint of the web API.
     * @param number    the number of objects to retrieve from the web API.
     * @return New instance of {@code OpenDataHubTourism}.
     */
    public static OpenDataHubTourism create(Endpoint endpoint, int number) {
        if(number < 0)
            number = 0;
        if(endpoint == null)
            endpoint = Endpoint.ACTIVITY;

        return new OpenDataHubTourism(endpoint, number);
    }
}
