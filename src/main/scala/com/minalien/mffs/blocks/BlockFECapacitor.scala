package com.minalien.mffs.blocks

import com.minalien.mffs.tiles.TileEntityFECapacitor
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemBlock
import net.minecraft.init.Items
import net.minecraftforge.common.util.ForgeDirection
import com.minalien.mffs.ModularForcefieldSystem

/**
 * Force Energy Capacitor
 */
object BlockFECapacitor extends MFFSMachineBlock("fecapacitor") {
	isRotationSensitive = true

	override def tileEntityClass = classOf[TileEntityFECapacitor]

	override def createNewTileEntity(world: World, metadata: Int) = new TileEntityFECapacitor

	override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, nX: Float,
	                              nY: Float, nZ: Float): Boolean = {
		val tileEntity = world.getTileEntity(x, y, z).asInstanceOf[TileEntityFECapacitor]

		if(player.isSneaking || (player.getHeldItem != null && player.getHeldItem.getItem.isInstanceOf[ItemBlock]))
			return false

		if(tileEntity == null || world.isRemote)
			return false

		if(player.getHeldItem != null && player.getHeldItem.getItem == Items.stick) {
			rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side))
			return true
		}

		player.openGui(ModularForcefieldSystem, 0, world, x, y, z)

		true
	}

}