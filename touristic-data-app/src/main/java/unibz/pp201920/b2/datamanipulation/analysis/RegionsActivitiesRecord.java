package unibz.pp201920.b2.datamanipulation.analysis;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class to represent the number of activities associated with the region Id/Ids.
 *
 * Used as a building block for the fields regionsWithMostActivities and
 * regionsWithLeastActivities of the {@link Analysis} class.
 *
 * @author Gioele De Vitti, Filippo Cenacchi, Samuel Dalvai
 */
public class RegionsActivitiesRecord {

    private final long numberOfActivities;
    private final TreeSet<String> regionIds;

    /**
     * Creates a new instance of {@code RegionsActivitiesRecord}.
     *
     * @param numberOfActivities How many activities the regions listed in {@code regionIds} have.
     * @param regionIds The regions that have {@code numberOfActivities} many activities.
     */
    public RegionsActivitiesRecord(long numberOfActivities, TreeSet<String> regionIds) {
        super();
        this.numberOfActivities = (numberOfActivities < 0 ? 0 : numberOfActivities);
        this.regionIds = regionIds;
    }

    public long getNumberOfActivities() {
        return numberOfActivities;
    }

    public Set<String> getRegionIds() {
        return regionIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionsActivitiesRecord that = (RegionsActivitiesRecord) o;
        return numberOfActivities == that.numberOfActivities &&
                Objects.equals(regionIds, that.regionIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfActivities, regionIds);
    }

}
