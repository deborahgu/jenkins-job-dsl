#!/usr/bin/env bash
set -ex

# Setup
cd $WORKSPACE/warehouse-transforms
# To install right version of dbt
pip install -r requirements.txt


cd $WORKSPACE/warehouse-transforms/projects/$DBT_PROJECT

# Choose the marts from which to transfer DBT models based on Jenkins job parameter.
if [ "$MODELS_TO_TRANSFER" = 'daily' ]
then
    MART_NAME=programs_reporting
elif [ "$MODELS_TO_TRANSFER" = 'enterprise' ]
    MART_NAME=enterprise
fi

ARGS="{mart: ${MART_NAME} }"

# Call DBT to perform all transfers for this mart.
dbt run-operation perform_s3_transfers --args "${ARGS}"
