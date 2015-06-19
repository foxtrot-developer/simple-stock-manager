package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.business.common.Constant;
import com.development.simplestockmanager.business.object.nullpackage.ClientNull;
import com.development.simplestockmanager.business.object.helper.ClientHelper;
import com.development.simplestockmanager.business.persistence.old.Client;
import com.development.simplestockmanager.business.persistence.controller.old.ClientJpaController;
import javax.persistence.Query;

/**
 * TESTED
 *
 * @author foxtrot
 */
public class ClientGeneralController {

    public long create(Client client) {
        try {
            ClientJpaController clientJpaController = ClientHelper.getJpaController();
            clientJpaController.create(client);
        } catch (Exception e) {
            client = new ClientNull();
        }

        return client.getId();
    }

    public Client read(Client client) {
        try {
            Query query = ClientHelper.getFindByIdQuery(client.getId());
            client = (Client) query.getSingleResult();
        } catch (Exception e) {
            client = new ClientNull();
        }

        return client;
    }

    public long update(Client client) {
        long status = Constant.UPDATE.FAILURE;

        if (read(client).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                ClientJpaController clientJpaController = ClientHelper.getJpaController();
                clientJpaController.edit(client);
                status = Constant.UPDATE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

    public long delete(Client client) {
        long status = Constant.DELETE.FAILURE;

        if (read(client).getId() != Constant.IDENTIFIER.INVALID) {
            try {
                ClientJpaController clientJpaController = ClientHelper.getJpaController();
                clientJpaController.destroy(client.getId());
                status = Constant.DELETE.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }

}
