package mcjty.xnet.multiblock;

/**
 * Every connected set of cables (so cables with the same color) will form a blob
 * in a chunk and has a local blob ID for that chunk.
 */
public class BlobId {

    private final int id;

    public BlobId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlobId blobId = (BlobId) o;

        if (id != blobId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
