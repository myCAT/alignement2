/**********
    Copyright © 2010-2012 Olanto Foundation Geneva

   This file is part of myCAT.

   myCAT is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of
    the License, or (at your option) any later version.

    myCAT is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with myCAT.  If not, see <http://www.gnu.org/licenses/>.

**********/

package org.olanto.mapman.server;

import java.io.IOException;
import java.io.Serializable;

/**
 * Pour le stockages des maps
 * 
 */
public class IntMap implements Serializable {

    public int[] from;
    public int[] to;

    public IntMap(int[] from, int[] to) {
        this.from = from;
        this.to = to;
    }

    /** construit une map identitÃ© */
    public IntMap(int size) {
        from = new int[size];
        to = new int[size];
        for (int i = 0; i < from.length; i++) {
            from[i] = i + 1;
            to[i] = i + 1;
        }
    }

    /** construit une map identitÃ© */
    public IntMap(int sizefrom, int sizeto) {
        from = new int[sizefrom];
        to = new int[sizeto];
        for (int i = 0; i < from.length; i++) {
            from[i] = ((i + 1) * sizeto) / sizefrom;
        }
        for (int i = 0; i < to.length; i++) {
            to[i] = ((i + 1) * sizefrom) / sizeto;
        }
    }

    /** construit une map transitive */
    public IntMap(IntMap sopi, IntMap pita) {
        from = new int[sopi.from.length];
        to = new int[pita.to.length];
        for (int i = 0; i < from.length; i++) {
            from[i] = pita.from[sopi.from[i]];
        }
        for (int i = 0; i < to.length; i++) {
            to[i] = sopi.to[pita.to[i]];
        }
    }

    /** pour Ã©changer les map */
    public IntMap swap() {

        int[] tempo = from;
        from = to;
        to = tempo;
        return this;
    }

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        out.writeObject(from);
        out.writeObject(to);
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        from = (int[]) in.readObject();
        to = (int[]) in.readObject();
    }
}
