/**
 * AC - A source-code copy detector
 *
 *     For more information please visit:  http://github.com/manuel-freire/ac
 *
 * ****************************************************************************
 *
 * This file is part of AC, version 2.0
 *
 * AC is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * AC is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AC.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * FreezeAction.java
 *
 * Created on April 30, 2004, 6:02 PM
 * Original Author: Manuel Freire (manuel.freire@uam.es)
 */

package es.ucm.fdi.clover.test;

import es.ucm.fdi.clover.event.StructureChangeEvent;
import es.ucm.fdi.clover.gui.actions.BaseAction;
import es.ucm.fdi.clover.model.Cluster;
import es.ucm.fdi.clover.model.ClusteredGraph;

import es.ucm.fdi.clover.gui.BaseInterface;
import es.ucm.fdi.clover.model.Edge;

/**
 * Increase cluster size (and therefore, decrease cluster number)
 *
 * @author  mfreire
 */
public class TestRemoveAction extends BaseAction {

	public TestRemoveAction(BaseInterface app) {
		super(app, "test delete", "img/delete.png",
				"Delete edge or vertex from the graph");
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {

		ClusteredGraph cg = (ClusteredGraph) getApp(e).getView().getBase();
		TestGraph tg = (TestGraph) cg.getBase();
		StructureChangeEvent sce = new StructureChangeEvent(tg);

		Object o = getOperand(e);
		if (o instanceof Cluster.Vertex) {
			// delete all children
			sce.getRemovedVertices().addAll(
					((Cluster.Vertex) o).getCluster().getLeafVertices());
			return;
		}
		if (o instanceof Edge) {
			// remove edge
			sce.getRemovedEdges().add((Edge) o);
		} else {
			// remove simple vertex
			sce.getRemovedVertices().add(o);
		}

		tg.structureChangePerformed(sce);
	}
}