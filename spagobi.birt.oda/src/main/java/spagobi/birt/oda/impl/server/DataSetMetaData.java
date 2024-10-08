/*
 *************************************************************************
 * Copyright (c) 2008 <<Your Company Name here>>
 *  
 *************************************************************************
 */

package spagobi.birt.oda.impl.server;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation class of IDataSetMetaData for an ODA runtime driver.
 * <br>
 * For demo purpose, the auto-generated method stubs have
 * hard-coded implementation that assume this custom ODA data set
 * is capable of handling a query that returns a single result set and 
 * accepts scalar input parameters by index.
 * A custom ODA driver is expected to implement own data set specific
 * behavior in its place. 
 */
public class DataSetMetaData implements IDataSetMetaData
{
	private IConnection m_connection;
	
	private static Logger logger = LoggerFactory.getLogger(DataSetMetaData.class);
		
	DataSetMetaData( IConnection connection )
	{
		m_connection = connection;
	}
	
	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getConnection()
	 */
	@Override
	public IConnection getConnection() throws OdaException
	{
		logger.debug("IN");
        // TODO Auto-generated method stub
		return m_connection;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceObjects(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public IResultSet getDataSourceObjects( String catalog, String schema, String object, String version ) throws OdaException
	{
		logger.debug("IN");
	    throw new UnsupportedOperationException();
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceMajorVersion()
	 */
	@Override
	public int getDataSourceMajorVersion() throws OdaException
	{
		logger.debug("IN");
        // TODO Auto-generated method stub
		return 1;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceMinorVersion()
	 */
	@Override
	public int getDataSourceMinorVersion() throws OdaException
	{
		logger.debug("IN");
        // TODO Auto-generated method stub
		return 0;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceProductName()
	 */
	@Override
	public String getDataSourceProductName() throws OdaException
	{
		logger.debug("IN");
        // TODO Auto-generated method stub
		return "SpagoBI Data Source";
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceProductVersion()
	 */
	@Override
	public String getDataSourceProductVersion() throws OdaException
	{
		logger.debug("IN");
		return Integer.toString( getDataSourceMajorVersion() ) + "." +   //$NON-NLS-1$
			   Integer.toString( getDataSourceMinorVersion() );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getSQLStateType()
	 */
	@Override
	public int getSQLStateType() throws OdaException
	{
		logger.debug("IN");
        // TODO Auto-generated method stub
		return IDataSetMetaData.sqlStateSQL99;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsMultipleResultSets()
	 */
	@Override
	public boolean supportsMultipleResultSets() throws OdaException
	{
		logger.debug("IN");
		return false;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsMultipleOpenResults()
	 */
	@Override
	public boolean supportsMultipleOpenResults() throws OdaException
	{
		logger.debug("IN");
		return false;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsNamedResultSets()
	 */
	@Override
	public boolean supportsNamedResultSets() throws OdaException
	{
		logger.debug("IN");
		return false;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsNamedParameters()
	 */
	@Override
	public boolean supportsNamedParameters() throws OdaException
	{
		logger.debug("IN");
        // TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsInParameters()
	 */
	@Override
	public boolean supportsInParameters() throws OdaException
	{
		logger.debug("IN");
        // TODO Auto-generated method stub
		return true;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsOutParameters()
	 */
	@Override
	public boolean supportsOutParameters() throws OdaException
	{
		logger.debug("IN");
        // TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getSortMode()
	 */
	@Override
	public int getSortMode()
	{
		logger.debug("IN");
        // TODO Auto-generated method stub
		return IDataSetMetaData.sortModeNone;
	}
    
}
