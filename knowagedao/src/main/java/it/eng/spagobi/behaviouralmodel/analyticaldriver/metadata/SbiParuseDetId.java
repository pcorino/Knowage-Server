/*
 * Knowage, Open Source Business Intelligence suite
 * Copyright (C) 2016 Engineering Ingegneria Informatica S.p.A.
 * 
 * Knowage is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Knowage is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.eng.spagobi.behaviouralmodel.analyticaldriver.metadata;

import it.eng.spagobi.commons.metadata.SbiExtRoles;




/**
 * SbiParuseDetId generated by hbm2java
 */
public class SbiParuseDetId  implements java.io.Serializable {

    // Fields    

     private SbiParuse sbiParuse;
     private SbiExtRoles sbiExtRoles;


    // Constructors

    /**
     * default constructor.
     */
    public SbiParuseDetId() {
    }
    
   
    
    

    // Property accessors

    /**
     * Gets the sbi paruse.
     * 
     * @return the sbi paruse
     */
    public SbiParuse getSbiParuse() {
        return this.sbiParuse;
    }
    
    /**
     * Sets the sbi paruse.
     * 
     * @param sbiParuse the new sbi paruse
     */
    public void setSbiParuse(SbiParuse sbiParuse) {
        this.sbiParuse = sbiParuse;
    }

    /**
     * Gets the sbi ext roles.
     * 
     * @return the sbi ext roles
     */
    public SbiExtRoles getSbiExtRoles() {
        return this.sbiExtRoles;
    }
    
    /**
     * Sets the sbi ext roles.
     * 
     * @param sbiExtRoles the new sbi ext roles
     */
    public void setSbiExtRoles(SbiExtRoles sbiExtRoles) {
        this.sbiExtRoles = sbiExtRoles;
    }

   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SbiParuseDetId) ) return false;
		 SbiParuseDetId castOther = ( SbiParuseDetId ) other; 
         
		 return (this.getSbiParuse()==castOther.getSbiParuse()) || ( this.getSbiParuse()!=null && castOther.getSbiParuse()!=null && this.getSbiParuse().equals(castOther.getSbiParuse()) )
 && (this.getSbiExtRoles()==castOther.getSbiExtRoles()) || ( this.getSbiExtRoles()!=null && castOther.getSbiExtRoles()!=null && this.getSbiExtRoles().equals(castOther.getSbiExtRoles()) );
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getSbiParuse().hashCode();
         result = 37 * result + this.getSbiExtRoles().hashCode();
         return result;
   }   


}