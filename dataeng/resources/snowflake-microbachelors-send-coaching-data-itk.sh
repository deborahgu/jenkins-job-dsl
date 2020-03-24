#!/usr/bin/env bash
set -ex

# setup
cd $WORKSPACE/analytics-tools/snowflake
pip install -r requirements/microbachelors.txt

# run the script twice to generate student and course reports for ITK
python send_coaching_data_itk.py \
    --key_path $KEY_PATH \
    --passphrase_path $PASSPHRASE_PATH \
    --user $USER \
    --account $ACCOUNT \
    --report_type student \
    --send False \
    --sftp_hostname $SFTP_HOSTNAME \
    --sftp_user $SFTP_USER \
    --sftp_password $SFTP_PASSWORD \
    --sftp_path $SFTP_STUDENT_PATH

python send_coaching_data_itk.py \
    --key_path $KEY_PATH \
    --passphrase_path $PASSPHRASE_PATH \
    --user $USER \
    --account $ACCOUNT \
    --report_type course \
    --send False \
    --sftp_hostname $SFTP_HOSTNAME \
    --sftp_user $SFTP_USER \
    --sftp_password $SFTP_PASSWORD \
    --sftp_path $SFTP_COURSE_PATH

