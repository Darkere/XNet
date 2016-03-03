package mcjty.xnet.client;

import com.google.common.primitives.Ints;
import mcjty.xnet.XNet;
import mcjty.xnet.multipart.RFConnectorPart;
import mcmultipart.client.multipart.ISmartMultipartModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.common.property.IExtendedBlockState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectorISBM implements ISmartMultipartModel {

    public static final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(XNet.MODID + ":energy_connector");

    @Override
    public IBakedModel handlePartState(IBlockState state) {
        // Called with the blockstate from our block. Here we get the values of the six properties and pass that to
        // our baked model implementation.
        IExtendedBlockState extendedBlockState = (IExtendedBlockState) state;
        EnumFacing side = extendedBlockState.getValue(RFConnectorPart.SIDE);
        return new BakedModel(side);
    }

    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing side) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAmbientOcclusion() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isGui3d() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBuiltInRenderer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        throw new UnsupportedOperationException();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ItemCameraTransforms getItemCameraTransforms() {
        throw new UnsupportedOperationException();
    }

    private static class BakedModel implements IBakedModel {

        public BakedModel(EnumFacing side) {
            this.side = side;
        }

        private static final TextureAtlasSprite spriteFace = XNet.ClientProxy.spriteEnergy;//, spriteSide = XNet.ClientProxy.spriteSide;
        private static final TextureAtlasSprite spriteSide = XNet.ClientProxy.spriteSide;//, spriteSide = XNet.ClientProxy.spriteSide;
        private final EnumFacing side;

        private int[] vertexToInts(double x, double y, double z, float u, float v, TextureAtlasSprite sprite) {
            return new int[] {
                    Float.floatToRawIntBits((float) x),
                    Float.floatToRawIntBits((float) y),
                    Float.floatToRawIntBits((float) z),
                    -1,
                    Float.floatToRawIntBits(sprite.getInterpolatedU(u)),
                    Float.floatToRawIntBits(sprite.getInterpolatedV(v)),
                    0
            };
        }

        private BakedQuad createQuad(Vec3 v1, Vec3 v2, Vec3 v3, Vec3 v4, TextureAtlasSprite sprite) {
            Vec3 normal = v1.subtract(v2).crossProduct(v3.subtract(v2));
            EnumFacing side = LightUtil.toSide((float) normal.xCoord, (float) normal.yCoord, (float) normal.zCoord);

            return new BakedQuad(Ints.concat(
                    vertexToInts(v1.xCoord, v1.yCoord, v1.zCoord, 0, 0, sprite),
                    vertexToInts(v2.xCoord, v2.yCoord, v2.zCoord, 0, 16, sprite),
                    vertexToInts(v3.xCoord, v3.yCoord, v3.zCoord, 16, 16, sprite),
                    vertexToInts(v4.xCoord, v4.yCoord, v4.zCoord, 16, 0, sprite)
            ), -1, side);
        }

        @Override
        public List<BakedQuad> getFaceQuads(EnumFacing side) {
            return Collections.emptyList();
        }

        @Override
        public List<BakedQuad> getGeneralQuads() {


            List<BakedQuad> quads = new ArrayList<>();
            double o = .4;      // Thickness of the cable. .0 would be full block, .5 is infinitely thin.
           // double p = .1;      // Thickness of the connector as it is put on the connecting block
           // double q = .2;      // The wideness of the connector

            switch (side) {
                case DOWN:
                    break;
                case UP:
                    break;
                case NORTH:
                    break;
                case SOUTH:
                    break;
                case WEST:
                    break;
                case EAST:
                    break;
            }

//                quads.add(createQuad(new Vec3(1 - o, 1 - o, o),     new Vec3(1 - o, 1,     o),     new Vec3(1 - o, 1,     1 - o), new Vec3(1 - o, 1 - o, 1 - o), spriteCable));

            return quads;
        }

        @Override
        public boolean isAmbientOcclusion() {
            return true;
        }

        @Override
        public boolean isGui3d() {
            return true;
        }

        @Override
        public boolean isBuiltInRenderer() {
            return false;
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return spriteSide;
        }

        @Override
        @SuppressWarnings("deprecation")
        public ItemCameraTransforms getItemCameraTransforms() {
            return ItemCameraTransforms.DEFAULT;
        }
    }
}
