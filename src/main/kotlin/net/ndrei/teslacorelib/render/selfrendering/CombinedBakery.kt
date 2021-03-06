package net.ndrei.teslacorelib.render.selfrendering

import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.vertex.VertexFormat
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.model.TRSRTransformation

class CombinedBakery(val bakeries: Iterable<IBakery>): IBakery {
    constructor(vararg bakeries: IBakery)
        : this(bakeries.asIterable())

    override fun getQuads(state: IBlockState?, stack: ItemStack?, side: EnumFacing?, vertexFormat: VertexFormat, transform: TRSRTransformation)
        = this.bakeries.fold(mutableListOf<BakedQuad>()) { list, bakery -> list.also { it.addAll(bakery.getQuads(state, stack, side, vertexFormat, transform)) } }
}
